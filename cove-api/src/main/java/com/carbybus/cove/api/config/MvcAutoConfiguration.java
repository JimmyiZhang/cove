package com.carbybus.cove.api.config;

import com.carbybus.infrastructure.configuration.UniteConfig;
import com.carbybus.infrastructure.configuration.UniteHttpConfig;
import com.carbybus.infrastructure.validator.CollectionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

/**
 * mvc 配置
 *
 * @author jimmy.zhang
 * @date 2019-04-09
 */
@Configuration
@EnableWebMvc
@ComponentScan({"com.carbybus.cove.*", "com.carbybus.infrastructure"})
public class MvcAutoConfiguration implements WebMvcConfigurer {
    @Autowired
    private UniteConfig config;

    @Override
    public Validator getValidator() {
        return new SpringValidatorAdapter(new CollectionValidator());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        UniteHttpConfig httpConfig = config.getHttpConfig();

        registry.addMapping("/**")
                .allowedMethods(httpConfig.getCorsAllowedMethods())
                .allowedOrigins(httpConfig.getCorsAllowedOrigins())
                .allowedHeaders(httpConfig.getCorsAllowedHeaders())
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor loggerInt = new LoggerInterceptor();
        registry.addInterceptor(loggerInt).addPathPatterns("/**");
    }
}
