package plus.cove.infrastructure.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
     * 幂等token名称
     */
    private String idempotentToken = "api-token";

    /**
     * 幂等cache名称
     */
    private String idempotentCache = "API_IDEMPOTENT";

    /**
     * 幂等有效时间，单位秒
     */
    private Integer idempotentDuration = 600;
}
