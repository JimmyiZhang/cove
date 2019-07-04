package com.carbybus.infrastructure.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * 缓存工具类
 * 仅支持Spring Boot方式的缓存
 * 与注解保持一致
 *
 * @author jimmy.zhang
 * @date 2019-05-09
 */
@Component
public class CacheUtils {
    @Autowired
    private CacheManager cacheManager;

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
     * 若缓存不存则获取数据并保存缓存
     * 与@Cacheable(key = "#id")保持一致
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-09
     */
    public <T> T get(final String name, final Object key, @Nullable Callable<T> callback) {
        return cacheManager.getCache(name).get(key, callback);
    }

    /**
     * 更新缓存
     * 与@CachePut(key = "#id")保持一致
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-09
     */
    public void put(final String name, final Object key, @Nullable Object value) {
        cacheManager.getCache(name).evict(key);
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
