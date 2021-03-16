package plus.cove.infrastructure.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
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
@Component
public class LogTracingInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(LogTracingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestCode = UUID.randomUUID().toString();
        // 设置请求编码
        response.addHeader("Request-Code", requestCode);
        log.info("请求开始, RequestCode: {}, Path: {}, method: {}",
                requestCode, request.getRequestURI(), request.getMethod());
        return true;
    }
}
