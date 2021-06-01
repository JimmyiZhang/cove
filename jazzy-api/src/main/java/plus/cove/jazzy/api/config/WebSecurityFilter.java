package plus.cove.jazzy.api.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import plus.cove.infrastructure.jwt.JwtAuthenticationToken;
import plus.cove.infrastructure.jwt.JwtClaim;
import plus.cove.infrastructure.jwt.JwtUtils;
import plus.cove.infrastructure.jwt.UniteJwtConfig;
import plus.cove.infrastructure.utils.RequestUtils;
import plus.cove.jazzy.domain.principal.UserRequest;

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
@Component
public class WebSecurityFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(WebSecurityFilter.class);

    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_HEAD = "HEAD";

    @Autowired
    private UniteJwtConfig jwtConfig;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RequestUtils requestUtils;

    /**
     * 获取jwt的token
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-09-06
     */
    private String getUserToken(HttpServletRequest request) {
        String authHeader = request.getHeader(jwtConfig.getTokenHeader());
        if (StringUtils.isEmpty(authHeader)) {
            authHeader = request.getParameter(jwtConfig.getTokenQuery());
        }

        if (StringUtils.isEmpty(authHeader)) {
            return authHeader;
        }
        return StringUtils.removeStart(authHeader, jwtConfig.getTokenBearer()).trim();
    }

    /**
     * 记录请求信息
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2021-06-01
     */
    private void logUserRequest(Long userId, HttpServletRequest request) {
        UserRequest ur = new UserRequest();
        ur.setUserId(userId);
        ur.setSource(requestUtils.getRemoteAddress(request));
        ur.setVersion(requestUtils.getClientVersion(request));
        ur.setRouter(String.format("%s:%s", request.getMethod(), request.getRequestURI()));
        log.info(ur.toString());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String method = request.getMethod();
        if (METHOD_OPTIONS.equals(method) || METHOD_HEAD.equals(method)) {
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authToken = getUserToken(request);
        // 没有认证信息，不处理
        if (StringUtils.isEmpty(authToken)) {
            chain.doFilter(request, response);
            return;
        }
        log.info("jwt认证开始，token: {}", authToken);

        JwtClaim claim = jwtUtils.decode(authToken);
        if (claim.getSuccess()) {
            String userId = claim.getClaim();
            String category = claim.getExtra();

            JwtAuthenticationToken jwtToken = new JwtAuthenticationToken(userId, category);
            SecurityContextHolder.getContext().setAuthentication(jwtToken);

            // 记录日志
            logUserRequest(Long.parseLong(userId), request);

            chain.doFilter(request, response);
        } else {
            throw new AccessDeniedException("无效用户");
        }
    }
}
