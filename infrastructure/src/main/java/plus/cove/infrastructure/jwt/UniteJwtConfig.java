package plus.cove.infrastructure.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * jwt配置
 * 设置jwt相关参数
 *
 * @author jimmy.zhang
 * @ 2019-04-28
 */
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("summer.jwt-config")
public class UniteJwtConfig {
    /**
     * Token过期时间，单位秒
     * 默认72小时
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration tokenExpired = Duration.ofSeconds(72 * 60 * 60L);

    /**
     * Token秘钥
     */
    private String tokenSecret;

    /**
     * Token主题
     */
    private String tokenSubject = "sub";

    /**
     * Token发行人
     */
    private String tokenIssue = "iss";

    /**
     * Token身份
     */
    private String tokenClaim = "id";

    /**
     * Token http header认证标识
     */
    private String tokenHeader = "Authorization";

    /**
     * Token http query认证标识
     */
    private String tokenQuery = "token";

    /**
     * Token Bearer认证标识
     */
    private String tokenBearer = "Bearer";
}
