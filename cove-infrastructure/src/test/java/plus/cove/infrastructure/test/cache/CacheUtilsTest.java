package plus.cove.infrastructure.test.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import plus.cove.infrastructure.caching.CacheUtils;
import plus.cove.infrastructure.caching.UniteCacheConfig;

import java.util.ArrayList;
import java.util.Collection;


/**
 * 缓存测试
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        CacheUtils.class,
        UniteCacheConfig.class,
        CaffeineCacheManager.class},
        initializers = ConfigDataApplicationContextInitializer.class)
@EnableCaching(proxyTargetClass = true)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class CacheUtilsTest {
    @Autowired
    CaffeineCacheManager cacheManager;

    @Autowired
    CacheUtils cacheUtils;

    @BeforeEach
    public void before() {
        Collection<String> caches = new ArrayList<>();
        caches.add("NAME");

        cacheManager.setCacheNames(caches);
    }

    public void tryGetNull() {
        String value = cacheUtils.get("NAME", "KEY1", String.class, () -> {
            System.out.println("KEY1 from callback, return null");
            return null;
        });
        System.out.println("KEY1 value is " + value);
    }

    public void tryGetObject() {
        String value = cacheUtils.get("NAME", "KEY2", String.class, () -> {
            System.out.println("KEY2 from callback, return ok");
            return "ok";
        });
        System.out.println("KEY2 value is " + value);
    }

    @Test
    public void tryGet() {
        for (int i = 0; i < 10; i++) {
            tryGetObject();
            tryGetNull();
        }
    }

    @Test
    public void putAndGet() {
        cacheUtils.put("NAME", "KEY3", "OK");

        String value = cacheUtils.get("NAME", "KEY3", String.class);
        System.out.println(value);
        Assertions.assertEquals("相等", value, "OK");
    }
}
