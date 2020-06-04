package plus.cove.infrastructure.interceptor;

import plus.cove.infrastructure.caching.CacheUtils;
import plus.cove.infrastructure.exception.BusinessException;
import plus.cove.infrastructure.exception.ValidatorError;
import plus.cove.infrastructure.security.UniteSecurityConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * 接口幂等性拦截器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Component
public class ApiIdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private UniteSecurityConfig securityConfig;
    @Autowired
    private CacheUtils cacheUtils;

    /**
     * cache名称
     */
    private String cacheName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        ApiIdempotent methodAnnotation = method.getAnnotation(ApiIdempotent.class);
        if (methodAnnotation != null) {
            check(request);
        }

        return true;
    }

    /**
     * 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    private void check(HttpServletRequest request) {
        // 从request获取token
        String token = request.getParameter(securityConfig.getIdempotentToken());
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(ValidatorError.IDEMPOTENT_TOKEN_MISSING);
        }

        // 获取cache
        if (StringUtils.isEmpty(cacheName)) {
            cacheName = String.format("%s#%d",
                    securityConfig.getIdempotentCache(),
                    securityConfig.getIdempotentDuration());
        }

        // 从cache中获取token
        Object tk = cacheUtils.get(cacheName, token);
        // 如果存在，则抛出异常
        if (tk != null) {
            throw new BusinessException(ValidatorError.IDEMPOTENT_TOKEN_DUPLICATED);
        }

        // 保存token到cache中
        cacheUtils.put(cacheName, token, "ok");
    }
}
