package com.carbybus.cove.api.config;

import com.carbybus.infrastructure.json.UniteJsonConfig;
import com.carbybus.infrastructure.redis.BaseRedisCacheManager;
import com.carbybus.infrastructure.redis.UniteRedisConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * redis配置
 * 配置key策略，过期时间
 *
 * @author jimmy.zhang
 * @date 2019-05-13
 */
@Configuration
public class RedisConfig {
    @Autowired
    private UniteRedisConfig config;

    @Autowired
    private UniteJsonConfig jsonConfig;

    @Bean("redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        template.setKeySerializer(keySerializer());
        template.setHashKeySerializer(keySerializer());
        template.setValueSerializer(valueSerializer());
        template.setHashValueSerializer(valueSerializer());

        return template;
    }

    @Primary
    @Bean
    @ConditionalOnProperty(prefix = "spring.cache", name = "type", havingValue = "REDIS")
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 缓存配置对象
        Duration ttl = Duration.ofMinutes(config.getExpiredMinutes());
        RedisCacheConfiguration rcConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(ttl)
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));

        return new BaseRedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                rcConfig);
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        ObjectMapper objectMapper = jsonConfig.getObjectMapper();
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }
}
