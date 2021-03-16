package plus.cove.infrastructure.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * cache配置
 *
 * @author jimmy.zhang
 * @ 2019-04-28
 */
@Data
@Configuration
@ConfigurationProperties("summer.redis-config")
public class UniteRedisConfig {
    /**
     * 分组分隔符
     * 默认分号
     */
    private String groupSeparator = ":";
}
