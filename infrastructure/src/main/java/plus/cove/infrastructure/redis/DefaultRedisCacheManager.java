package plus.cove.infrastructure.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

/**
 * 基本redis缓存管理
 * 支持过期时间，单位分钟，格式如下：
 * Cacheable(value="cacheName#10")
 * CacheUtils同样适用
 *
 * @author jimmy.zhang
 * @date 2019-05-13
 */
@Slf4j
public class DefaultRedisCacheManager extends RedisCacheManager {
    @Autowired
    UniteRedisConfig redisConfig;

    // 缓存长度
    private static final int NAME_LENGTH = 2;

    public DefaultRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration, true);
    }

    /**
     * 重置缓存名称，支持多租户
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    @Override
    public Cache getCache(String name) {
        String cacheName = this.handlerCacheName(name);
        return super.getCache(cacheName);
    }

    /**
     * 处理Cache名称，支持多租户
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    protected String handlerCacheName(String name) {
        return name;
    }

    /**
     * 设置超时时间
     * 根据规则获取超时时间，并进行赋值
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        long cacheAge = redisConfig.getExpiredSeconds();
        String cacheTtl = redisConfig.getTtlSeparator();

        // 获取过期时间
        if (StringUtils.isNotEmpty(name) && name.contains(cacheTtl)) {
            String[] cacheNames = name.split(cacheTtl);
            if (cacheNames.length == NAME_LENGTH) {
                cacheAge = Long.parseLong(cacheNames[1]);
                name = cacheNames[0];
            }
        }

        if (cacheConfig != null && cacheAge > 0) {
            log.info("重置缓存参数，缓存名称：{}，过期时间：{}", name, cacheAge);
            cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(cacheAge));
        }
        return super.createRedisCache(name, cacheConfig);
    }
}