package com.carbybus.cove.api.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

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
@EnableCaching
@MapperScan(basePackages = "com.carbybus.infrastructure.caching")
public class CachingConfig {

}
