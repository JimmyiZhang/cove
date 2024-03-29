package plus.cove.infrastructure.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * 安全配置
 * 设置安全相关参数
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Data
@Configuration
@ConfigurationProperties("summer.security-config")
public class UniteSecurityConfig {
    /**
     * 允许地址
     */
    private String[] permitUrls;

    /**
     * 监控地址
     */
    private String actuatorUrl = "/actuator";

    /**
     * 监控角色
     */
    private String actuatorRole = "ACTUATOR";

    /**
     * 幂等token名称
     */
    private String idempotentToken = "api-idempotent";

    /**
     * 幂等cache名称
     */
    private String idempotentCache = "API_IDEMPOTENT";

    /**
     * 幂等有效时间，单位秒
     * 默认5秒
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration idempotentDuration = Duration.ofSeconds(5L);
}
