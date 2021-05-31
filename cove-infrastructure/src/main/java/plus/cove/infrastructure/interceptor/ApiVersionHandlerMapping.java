package plus.cove.infrastructure.interceptor;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 接口版本处理
 * <p>
 *
 * @author jimmy.zhang
 * @since 2.0
 */
public class ApiVersionHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected boolean isHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class);
    }

    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        // 方法注解优先
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        if (apiVersion == null) {
            apiVersion = AnnotationUtils.findAnnotation(method.getDeclaringClass(), ApiVersion.class);
        }

        if (apiVersion == null || apiVersion.source() == null) {
            super.registerHandlerMethod(handler, method, mapping);
        }

        ApiVersionSource source = apiVersion.source();
        switch (source) {
            case PATH:
                String[] path = apiVersion.value();
                PatternsRequestCondition pathCondition = new PatternsRequestCondition(path)
                        .combine(mapping.getPatternsCondition());
                mapping = new RequestMappingInfo(mapping.getName(), pathCondition, mapping.getMethodsCondition(),
                        mapping.getParamsCondition(), mapping.getHeadersCondition(), mapping.getConsumesCondition(),
                        mapping.getProducesCondition(), mapping.getCustomCondition());
                break;
            case HEADER:
                String header = String.format("%s=%s", apiVersion.sourceKey(), apiVersion.value());
                HeadersRequestCondition headerCondition = new HeadersRequestCondition(header)
                        .combine(mapping.getHeadersCondition());
                mapping = new RequestMappingInfo(mapping.getName(), mapping.getPatternsCondition(), mapping.getMethodsCondition(),
                        mapping.getParamsCondition(), headerCondition, mapping.getConsumesCondition(),
                        mapping.getProducesCondition(), mapping.getCustomCondition());
                break;
        }
        super.registerHandlerMethod(handler, method, mapping);
    }
}
