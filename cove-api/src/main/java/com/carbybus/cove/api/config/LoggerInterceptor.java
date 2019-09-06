package com.carbybus.cove.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 日志拦截器
 * <p>
 * 可以设置缓存
 * response.addHeader("Cache-Control", "max-age=60000");
 * </p>
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
        String requestCode= UUID.randomUUID().toString();
        // 设置请求编码
        response.addHeader("Request-Code", requestCode);

        log.info("请求开始, RequestCode: {}, Path: {}, method: {}",
                requestCode, path, method);
        return true;
    }
}
