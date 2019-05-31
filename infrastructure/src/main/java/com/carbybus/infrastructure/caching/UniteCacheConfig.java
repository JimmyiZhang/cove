package com.carbybus.infrastructure.caching;

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
     * cache过期时间，单位分钟
     * 默认72小时
     */
    private long expiredMinutes = 72 * 60L;
}
