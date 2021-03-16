package plus.cove.jazzy.api.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger 配置
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Configuration
@EnableOpenApi
@Profile({"develop"})
public class SwaggerConfig {
    @Bean
    public Docket createDocket() {
        ApiInfo info = this.getApiInfo();
        List<RequestParameter> parameters = this.getParameters();

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(info)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(parameters);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("接口文档")
                .version("1.0")
                .build();
    }

    private List<RequestParameter> getParameters() {
        RequestParameterBuilder builder = new RequestParameterBuilder();
        RequestParameter parameter = builder.name("Authorization")
                .in(ParameterType.HEADER)
                .description("认证token")
                .required(false)
                .build();

        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(parameter);
        return parameters;
    }
}