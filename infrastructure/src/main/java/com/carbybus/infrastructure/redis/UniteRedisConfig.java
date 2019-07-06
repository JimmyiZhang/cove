package com.carbybus.infrastructure.redis;

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
     * 过期时间，单位分钟
     * 默认无过期
     */
    private long expiredMinutes = 30 * 24 * 60;

    /**
     * 分组分隔符
     * 默认分号
     */
    private String groupSeparator = ":";
}
