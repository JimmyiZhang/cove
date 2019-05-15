package com.carbybus.cove.api.config;

import com.carbybus.infrastructure.configuration.UniteJwtConfig;
import com.carbybus.infrastructure.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UniteJwtConfig jwtConfig;

    // 获取jwt的token
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authToken = getJwtToken(request);
        // 没有认证信息，不处理
        if (StringUtils.isEmpty(authToken)) {
            chain.doFilter(request, response);
            return;
        }
        log.info("jwt认证开始，token: {}", authToken);
        String claim = JwtUtils.decode(authToken);

        JwtAuthenticationToken jwtToken = new JwtAuthenticationToken(claim);
        jwtToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(jwtToken);
        chain.doFilter(request, response);
    }
}
