package plus.cove.infrastructure.caching;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.Callable;

/**
 * 缓存工具类
 * 仅支持Spring Boot方式的缓存
 * 与注解保持一致
 *
 * @author jimmy.zhang
 * @date 2019-05-09
 */
@Slf4j
@Component
public class CacheUtils {
    @Autowired
    UniteCacheConfig cacheConfig;

    @Autowired
    CacheManager cacheManager;

    /**
     * 获取缓存
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-09
     */
    public Object get(final String name, final Object key) {
        Cache.ValueWrapper vw = cacheManager.getCache(name).get(key);
        if (vw != null) {
            return vw.get();
        }
        return null;
    }

    /**
     * 获取缓存同时指定转换类型
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-09
     */
    public <T> T get(final String name, final Object key, @Nullable Class<T> tClass) {
        return cacheManager.getCache(name).get(key, tClass);
    }

    /**
     * 获取缓存
     * 若缓存不存则调用回调并保存缓存
     * 支持回调返回null类型，从而防止缓存击穿（默认保持2秒）
     * null类型将保持UniteCacheConfig.nullRetainSeconds时间
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-09
     */
    public <T> T get(final String name, final Object key, @Nullable Class<T> tClass, @Nullable Callable<T> callback) {
        Cache.ValueWrapper element = cacheManager.getCache(name).get(key);
        Object value = element == null ? null : element.get();

        T result = null;
        // 缓存有值
        if (value != null) {
            if (tClass == null) {
                throw new IllegalStateException("cache type error");
            }
            // 类型相符，直接返回
            if (tClass.isInstance(value)) {
                result = (T) value;
            }
            // 空类型，判断有效期
            if (CacheNullObject.class.isInstance(value)) {
                log.info("cache [{}:{}] is null", name, key);
                CacheNullObject nullObj = (CacheNullObject) value;
                if (nullObj.getExpiredTime().isBefore(Instant.now())) {
                    value = null;
                } else {
                    result = null;
                }
            }
        }

        // 缓存无值
        if (value == null) {
            try {
                result = callback.call();
                if (result == null) {
                    CacheNullObject nullObj = new CacheNullObject();
                    nullObj.setExpiredTime(Instant.now().plusSeconds(cacheConfig.getNullRetainSeconds()));
                    cacheManager.getCache(name).put(key, nullObj);
                } else {
                    cacheManager.getCache(name).put(key, result);
                }
            } catch (Exception ex) {
                throw new IllegalStateException("callback call error", ex);
            }
        }
        return result;
    }

    /**
     * 更新缓存
     * 不允许有null值
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-09
     */
    public void put(final String name, final Object key, @Nullable Object value) {
        cacheManager.getCache(name).put(key, value);
    }

    /**
     * 删除缓存
     * 与@CacheEvict(key = "#id")保持一致
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-09
     */
    public void evict(final String name, final Object key) {
        cacheManager.getCache(name).evict(key);
    }

    /**
     * 清除缓存
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-09
     */
    public void clear(final String name) {
        cacheManager.getCache(name).clear();
    }
}
