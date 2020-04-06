package plus.cove.jazzy.api.config;

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
import plus.cove.infrastructure.redis.DefaultRedisCacheConfig;
import plus.cove.infrastructure.redis.DefaultRedisCacheManager;

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
    private DefaultRedisCacheConfig defaultConfig;

    @Bean("redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        template.setKeySerializer(defaultConfig.keySerializer());
        template.setHashKeySerializer(defaultConfig.keySerializer());
        template.setValueSerializer(defaultConfig.valueSerializer());
        template.setHashValueSerializer(defaultConfig.valueSerializer());

        return template;
    }

    @Primary
    @Bean
    @ConditionalOnProperty(prefix = "spring.cache", name = "type", havingValue = "REDIS")
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = defaultConfig.cacheConfig();
        return new DefaultRedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                config);
    }
}
