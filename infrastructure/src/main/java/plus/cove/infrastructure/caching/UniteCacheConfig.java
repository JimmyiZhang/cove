package plus.cove.infrastructure.caching;

import lombok.Data;
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
}
