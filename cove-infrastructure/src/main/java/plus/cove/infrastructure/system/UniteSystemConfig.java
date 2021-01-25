package plus.cove.infrastructure.system;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 系统配置
 * 包括运行环境，默认密码等
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Data
@Configuration
@ConfigurationProperties("summer.system-config")
public class UniteSystemConfig {
    /**
     * 运行环境
     */
    private SystemRunTime runTime = SystemRunTime.DEVELOPMENT;

    /**
     * 默认认证码
     */
    private String authCode = "1234";
}
