package plus.cove.jazzy.api.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger 配置
 *
 * @author jimmy.zhang
 * @date 2019-04-04
 */
@Configuration
@EnableSwagger2
@Profile({"develop", "test"})
public class SwaggerConfig {
    private final String excludes = "/error";

    @Bean
    public Docket createRestApi() {
        ApiInfo info = this.getApiInfo();
        List<Parameter> parameters = this.getParameters();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.getApiInfo())
                .select()
                .apis(this.getRequests())
                .paths(this.getPaths())
                .build()
                .globalOperationParameters(this.getParameters());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("项目接口文档")
                .version("1.0")
                .build();
    }

    private List<Parameter> getParameters() {
        ParameterBuilder builder = new ParameterBuilder();
        builder.name("Authorization")
                .description("认证token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> parameters = new ArrayList<>();
        parameters.add(builder.build());
        return parameters;
    }

    private Predicate<String> getPaths() {
        return input -> excludes.indexOf(input) == -1;
    }

    private Predicate<RequestHandler> getRequests() {
        return RequestHandlerSelectors.basePackage("plus.cove.jazzy.api.controller");
    }
}
