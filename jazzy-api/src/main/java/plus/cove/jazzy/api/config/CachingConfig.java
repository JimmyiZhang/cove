package plus.cove.jazzy.api.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plus.cove.infrastructure.caching.DefaultCaffeineCacheManager;

/**
 * 缓存配置
 * 使用proxy的AOP模式会导致内部调用缓存失效
 * proxyTargetClass=true, 强制使用CGLIB代理
 * <p>
 * 执行方法出现异常，会导致缓存清空不被执行
 * 推荐CacheEvict注解使用beforeInvocation=true
 *
 * @author jimmy.zhang
 * @date 2019-05-08
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class CachingConfig {
    @Bean
    @ConditionalOnProperty(prefix = "spring.cache", name = "type", havingValue = "CAFFEINE")
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new DefaultCaffeineCacheManager();
        cacheManager.setAllowNullValues(false);
        return cacheManager;
    }
}
