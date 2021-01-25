package plus.cove.infrastructure.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import plus.cove.infrastructure.caching.UniteCacheConfig;
import plus.cove.infrastructure.json.UniteJsonConfig;

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
@Component
public class DefaultRedisCacheConfig {
    @Autowired
    private UniteCacheConfig redisConfig;
    @Autowired
    private UniteJsonConfig jsonConfig;

    private RedisSerializer<String> keySerializer;
    private RedisSerializer<Object> valueSerializer;
    private RedisCacheConfiguration cacheConfig;

    public RedisCacheConfiguration cacheConfig() {
        if (this.cacheConfig == null) {
            this.cacheConfig = RedisCacheConfiguration
                    .defaultCacheConfig()
                    .entryTtl(redisConfig.getDurationTime())
                    .disableCachingNullValues()
                    .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));
        }

        return this.cacheConfig;
    }

    public RedisSerializer<String> keySerializer() {
        if (this.keySerializer == null) {
            this.keySerializer = new StringRedisSerializer();
        }
        return this.keySerializer;
    }

    public RedisSerializer<Object> valueSerializer() {
        if (this.valueSerializer == null) {
            ObjectMapper objectMapper = jsonConfig.getRedisMapper();
            this.valueSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        }
        return this.valueSerializer;
    }
}
