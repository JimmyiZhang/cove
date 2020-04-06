package plus.cove.jazzy.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.config.annotation.*;
import plus.cove.infrastructure.converter.LocalDateConverter;
import plus.cove.infrastructure.converter.LocalDateTimeConverter;
import plus.cove.infrastructure.http.UniteHttpConfig;
import plus.cove.infrastructure.interceptor.ApiIdempotentInterceptor;
import plus.cove.infrastructure.interceptor.LogTracingInterceptor;
import plus.cove.infrastructure.json.UniteJsonConfig;
import plus.cove.infrastructure.validator.CollectionValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * mvc 配置
 *
 * @author jimmy.zhang
 * @date 2019-04-09
 */
@Configuration
@EnableWebMvc
@ComponentScan({"plus.cove.jazzy.*", "plus.cove.infrastructure.*"})
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UniteHttpConfig httpConfig;
    @Autowired
    private UniteJsonConfig jsonConfig;
    @Autowired
    private LogTracingInterceptor logInterceptor;
    @Autowired
    private ApiIdempotentInterceptor apiInterceptor;

    @Override
    public Validator getValidator() {
        return new SpringValidatorAdapter(new CollectionValidator());
    }

    /**
     * 配置静态资源，可以直接访问
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 配置跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .maxAge(httpConfig.getCorsMaxAge())
                .allowedMethods(httpConfig.getCorsAllowedMethods())
                .allowedOrigins(httpConfig.getCorsAllowedOrigins())
                .allowedHeaders(httpConfig.getCorsAllowedHeaders())
                .allowCredentials(true);
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
        registry.addInterceptor(apiInterceptor).addPathPatterns("/**");
    }

    /**
     * 配置格式化
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, LocalDate.class, new LocalDateConverter(jsonConfig.getDateFormat()));
        registry.addConverter(String.class, LocalDateTime.class, new LocalDateTimeConverter(jsonConfig.getDatetimeFormat()));
    }

    /**
     * 配置消息转化
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 统一json格式化
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jsonConfig.getJsonMapper();
        jsonConverter.setObjectMapper(objectMapper);
        converters.add(0, jsonConverter);
    }
}
