package plus.cove.infrastructure.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import plus.cove.infrastructure.caching.UniteCacheConfig;
import plus.cove.infrastructure.json.UniteJsonConfig;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author jimmy.zhang
 * @date 2019-05-13
 */
@Component
public class RedisUtils {
    private final Logger log = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    UniteCacheConfig redisConfig;
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
        if (!keys.isEmpty()) {
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

        ObjectMapper mapper = jsonConfig.getJsonMapper();
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
        return set(key, value, redisConfig.getDurationTime().toMillis());
    }

    /**
     * 写入redis值
     *
     * @param key     key值
     * @param value   value值
     * @param timeout 过期时间，单位毫秒
     * @return 是否写入成功
     * @author jimmy.zhang
     * @date 2019-05-13
     */
    public boolean set(final String key, Object value, Long timeout) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);

            if (timeout != null && timeout > 0) {
                redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
            }

            result = true;
            log.debug("redis写入缓存正常, key={}, value={}, timeout={}ms", key, Objects.toString(value), timeout);
        } catch (Exception e) {
            log.error("redis写入缓存异常, key={}, value={}, timeout={}ms", key, Objects.toString(value), timeout);
        }
        return result;
    }
}
