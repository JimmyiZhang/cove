package plus.cove.infrastructure.interceptor;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 接口版本处理
 * <p>
 * 根据类或方法上@RequestMapping生成RequestMappingInfo
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

        // 初始化url
        String[] urlPatterns = apiVersion == null ? new String[0] : apiVersion.value();

        // 构建mapping
        PatternsRequestCondition apiPattern = new PatternsRequestCondition(urlPatterns);
        PatternsRequestCondition oriPattern = mapping.getPatternsCondition();
        PatternsRequestCondition finalPattern = apiPattern.combine(oriPattern);
        mapping = new RequestMappingInfo(mapping.getName(), finalPattern, mapping.getMethodsCondition(),
                mapping.getParamsCondition(), mapping.getHeadersCondition(), mapping.getConsumesCondition(),
                mapping.getProducesCondition(), mapping.getCustomCondition());
        super.registerHandlerMethod(handler, method, mapping);
    }
}
