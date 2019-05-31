package com.carbybus.cove.api.config;

import com.carbybus.infrastructure.http.UniteHttpConfig;
import com.carbybus.infrastructure.json.UniteJsonConfig;
import com.carbybus.infrastructure.validator.CollectionValidator;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * mvc 配置
 *
 * @author jimmy.zhang
 * @date 2019-04-09
 */
@Configuration
@ComponentScan({"com.carbybus.cove.*", "com.carbybus.infrastructure"})
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UniteHttpConfig httpConfig;

    @Autowired
    private UniteJsonConfig jsonConfig;

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
        registry.addMapping("/**")
                .maxAge(httpConfig.getCorsMaxAge())
                .allowedMethods(httpConfig.getCorsAllowedMethods())
                .allowedOrigins(httpConfig.getCorsAllowedOrigins())
                .allowedHeaders(httpConfig.getCorsAllowedHeaders())
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor logInterceptor = new LoggerInterceptor();
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // jackson统一格式化
        MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(jsonConfig.getDateFormat());
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(jsonConfig.getDatetimeFormat());

        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(datetimeFormatter));
        timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(datetimeFormatter));
        objectMapper.disable(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS)
                .registerModule(timeModule);

        // Long类型
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);

        jackson2Converter.setObjectMapper(objectMapper);
        converters.add(0, jackson2Converter);
    }
}
