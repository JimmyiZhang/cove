package com.carbybus.infrastructure.redis;

import com.carbybus.infrastructure.configuration.UniteJsonConfig;
import com.carbybus.infrastructure.configuration.UniteRedisConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author jimmy.zhang
 * @date 2019-05-13
 */
@Slf4j
@Component
public class RedisUtils {
    @Autowired
    UniteRedisConfig redisConfig;
    @Autowired
    UniteJsonConfig jsonConfig;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 判断是否存在
     *
     * @param key key值
     * @return 是否存在
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除对应的值
     *
     * @param key key值
     * @return
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    public void remove(final String key) {
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 批量删除
     *
     * @param keys key值
     * @return
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 模式删除
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    public void removePattern(final String pattern) {
        Set<Object> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 读取redis值
     *
     * @param key key值
     * @return value值
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 读取redis值
     *
     * @param key key值
     * @return value值
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    public <T> T get(final String key, Class<T> tClass) {
        Object object = redisTemplate.opsForValue().get(key);

        ObjectMapper mapper = jsonConfig.getObjectMapper();
        return mapper.convertValue(object, tClass);
    }

    /**
     * 写入redis值
     *
     * @param key   key值
     * @param value value值
     * @return 是否写入成功
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    public boolean set(final String key, Object value) {
        return set(key, value, redisConfig.getExpiredMinutes());
    }

    /**
     * 写入redis值
     *
     * @param key           key值
     * @param value         value值
     * @param expireMinutes 过期分钟数
     * @return 是否写入成功
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    public boolean set(final String key, Object value, Long expireMinutes) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);

            if (expireMinutes != null && expireMinutes > 0) {
                redisTemplate.expire(key, expireMinutes, TimeUnit.MINUTES);
            }

            result = true;
            log.info("redis写入缓存正常, key={}, value={}, expired={}", key, Objects.toString(value), expireMinutes);
        } catch (Exception e) {
            log.error("redis写入缓存异常, key={}, value={}, expired={}", key, Objects.toString(value), expireMinutes);
        }
        return result;
    }
}
