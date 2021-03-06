package plus.cove.infrastructure.redis;

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
@ConfigurationProperties("summer.redis-config")
public class UniteRedisConfig {
    /**
     * 分组分隔符
     * 默认分号
     */
    private String groupSeparator = ":";

    /**
     * 过期时间，单位秒
     * 默认30天
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration durationTime = Duration.ofDays(30L);
}
