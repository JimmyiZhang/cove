package plus.cove.infrastructure.caching;

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
@ConfigurationProperties("summer.cache-config")
public class UniteCacheConfig {
    /**
     * 最大cache过期时间，单位秒
     * 默认72小时
     */
    private long maxExpiredSeconds = 72 * 60 * 60L;

    /**
     * 空值保留时间，单位秒
     */
    private long nullRetainSeconds = 2L;
}
