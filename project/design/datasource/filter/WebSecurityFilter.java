package com.gongzuolaile.agent.api.config;

import com.gongzuolaile.agent.application.SecurityApplication;
import com.gongzuolaile.agent.domain.entity.security.SecurityToken;
import com.gongzuolaile.infrastructure.datasource.CustomerDataSourceHolder;
import com.gongzuolaile.infrastructure.datasource.CustomerDataSourceProcessor;
import com.gongzuolaile.infrastructure.jwt.JwtAuthenticationToken;
import com.gongzuolaile.infrastructure.jwt.JwtClaim;
import com.gongzuolaile.infrastructure.jwt.JwtUtils;
import com.gongzuolaile.infrastructure.jwt.UniteJwtConfig;
import com.gongzuolaile.infrastructure.security.UniteSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt认证过滤器
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @date 2019-09-06
 */
@Slf4j
@Component
public class WebSecurityFilter extends OncePerRequestFilter {
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_HEAD = "HEAD";

    @Autowired
    CustomerDataSourceProcessor dataSourceProcessor;
    @Autowired
    UniteJwtConfig jwtConfig;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UniteSecurityConfig securityConfig;
    @Autowired
    SecurityApplication securityApp;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 获取jwt的token
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-09-06
     */
    private String getJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader(jwtConfig.getTokenHeader());
        if (StringUtils.isEmpty(authHeader)) {
            authHeader = request.getParameter(jwtConfig.getTokenQuery());
        }

        if (StringUtils.isEmpty(authHeader)) {
            return authHeader;
        }
        return StringUtils.removeStart(authHeader, jwtConfig.getTokenBearer()).trim();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // 无论是否过滤，都需要获取datasource
        String productCode = dataSourceProcessor.getProductCode(request);
        // todo: 测试阶段，没有默认是primary
        if (StringUtils.isEmpty(productCode)) {
            productCode = "primary";
        }
        CustomerDataSourceHolder.setKey(productCode);

        String method = request.getMethod();
        if (METHOD_OPTIONS.equals(method) || METHOD_HEAD.equals(method)) {
            return true;
        }

        String requestUri = request.getRequestURI();
        String[] pathSources = this.securityConfig.getPermitUrls();
        boolean skip = false;
        for (int i = 0; i < pathSources.length; i++) {
            if (pathMatcher.match(pathSources[i], requestUri)) {
                skip = true;
                break;
            }
        }

        return skip;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authToken = getJwtToken(request);
        // 没有认证信息，不处理
        if (StringUtils.isEmpty(authToken)) {
            chain.doFilter(request, response);
            return;
        }
        log.info("jwt认证开始，token: {}", authToken);

        JwtClaim claim = jwtUtils.decode(authToken);
        if (claim.getSuccess()) {
            String userId = claim.getClaim();
            String extra = claim.getExtra();

            // 检查有效性
            Long uid = Long.parseLong(userId);
            SecurityToken token = securityApp.findUserToken(uid);
            boolean valid = SecurityToken.valid(token, authToken);
            if (!valid) {
                throw new AccessDeniedException("无效用户");
            }

            JwtAuthenticationToken jwt = new JwtAuthenticationToken(userId, extra, extra);
            SecurityContextHolder.getContext().setAuthentication(jwt);
            chain.doFilter(request, response);
        } else {
            throw new AccessDeniedException("无效用户");
        }
    }
}
