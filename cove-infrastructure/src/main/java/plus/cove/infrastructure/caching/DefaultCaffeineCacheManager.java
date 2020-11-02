package plus.cove.infrastructure.caching;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;

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
public class DefaultCaffeineCacheManager extends CaffeineCacheManager {
    @Autowired
    UniteCacheConfig cacheConfig;

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
    protected Cache createCaffeineCache(String name) {
        CacheResetResult reset = cacheConfig.resetCache(name);
        log.debug("重置缓存参数，缓存名称：{}->{}", name, reset.getName());

        com.github.benmanes.caffeine.cache.Cache caffeine =
                Caffeine.newBuilder()
                        .expireAfter(new Expiry<Object, Object>() {
                            @Override
                            public long expireAfterCreate(Object key, Object value, long currentTime) {
                                log.debug("重置缓存参数，过期时间：{}s->{}s",
                                        currentTime / 1_000_000_000L, reset.getExpire().getSeconds());
                                return reset.getExpire().toNanos();
                            }

                            @Override
                            public long expireAfterUpdate(Object key, Object value, long currentTime, long currentDuration) {
                                log.debug("重置缓存参数，剩余时间：{}，过期时间：{}s->{}s",
                                        currentDuration / 1_000_000_000L,
                                        currentTime / 1_000_000_000L,
                                        reset.getExpire().getSeconds());
                                return reset.getExpire().toNanos();
                            }

                            @Override
                            public long expireAfterRead(Object key, Object value, long currentTime, long currentDuration) {
                                log.debug("重置缓存参数，剩余时间：{}，过期时间：{}s",
                                        currentDuration / 1_000_000_000L,
                                        currentTime / 1_000_000_000L);
                                return currentDuration;
                            }
                        })
                        .build();

        return new CaffeineCache(reset.getName(), caffeine, this.isAllowNullValues());
    }
}
