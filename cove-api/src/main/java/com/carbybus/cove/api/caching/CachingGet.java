package com.carbybus.cove.api.caching;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CachingGet {
    /**
     * 缓存名
     * 如果是本地缓存，name相同的缓存数据存放在同一个map中
     * 如果是redis缓存，缓存的key将会加上name作为前缀,为：{name}:key
     *
     * @return
     */
    String name();

    /**
     * 缓存key表达式，支持常量字符串／请求参数表达式
     *
     * @return
     */
    String key();

    /**
     * 存储类型：Local（本地缓存） or  Redis缓存
     *
     * @return
     */
    CachingStorage storage() default CachingStorage.LOCAL;

    /**
     * 缓存存活时间，单位秒：支持数字／数字表达式或本地配置文件参数
     * 例如：${orderCacheTTL:10},其中orderCacheTTL为本地配置文件中的配置参数，10为默认值
     *
     * @return
     */
    String live() default "10";
}
