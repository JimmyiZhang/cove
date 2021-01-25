package plus.cove.infrastructure.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import plus.cove.infrastructure.caching.CacheResetResult;
import plus.cove.infrastructure.caching.UniteCacheConfig;

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
    UniteCacheConfig cacheConfig;

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
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration configuration) {
        CacheResetResult reset = cacheConfig.resetCache(name);

        if (configuration != null) {
            log.debug("重置缓存参数，缓存名称：{}->{}，过期时间：{}ms", name,
                    reset.getName(), reset.getExpire().toMillis());
            configuration = configuration.entryTtl(reset.getExpire());
        }
        return super.createRedisCache(reset.getName(), configuration);
    }
}
