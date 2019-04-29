package com.carbybus.cove.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 *
 * @author jimmy.zhang
 * @date 2019-04-29
 */
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String method = request.getMethod();
        String path = request.getRequestURI();
        // todo: 可以记录日志
        log.debug("the request url is: {}", path);

        // todo: 可以设置缓存
        response.addHeader("Cache-Control", "max-age=60000");

        return true;
    }
}
