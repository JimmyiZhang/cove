package plus.cove.infrastructure.caching;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

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
    // 缓存长度
    private static final int CACHE_DURATION_LENGTH = 2;

    /**
     * 最大cache过期时间，单位秒
     * 默认72小时
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration maxDurationTime = Duration.ofHours(72L);

    /**
     * 空值保留时间，单位秒
     * 默认2秒
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration nullDurationTime = Duration.ofSeconds(2L);

    /**
     * 过期时间，单位秒
     * 默认30天
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration durationTime = Duration.ofDays(30L);

    /**
     * 过期时间分割符
     */
    private String ttlSeparator = "#";

    /**
     * 重置缓存
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public CacheResetResult resetCache(String name) {
        CacheResetResult result = new CacheResetResult();
        result.setExpire(this.durationTime);
        result.setName(name);

        // 获取过期时间
        if (StringUtils.isNotEmpty(name) && name.contains(ttlSeparator)) {
            String[] cacheNames = name.split(ttlSeparator);
            if (cacheNames.length == CACHE_DURATION_LENGTH) {
                long maxAge = Long.parseLong(cacheNames[1]);
                if (maxAge > 0) {
                    Duration duration = Duration.ofSeconds(maxAge);
                    result.setExpire(duration);
                }
                name = cacheNames[0];
                result.setName(name);
            }
        }

        return result;
    }
}
