package com.carbybus.cove.api.config;

import com.carbybus.infrastructure.validator.CollectionValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
}
