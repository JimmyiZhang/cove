package com.gongzuolaile.infrastructure.datasource;


/**
 * 切换数据源
 * 基于ThreadLocal
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public final class CustomerDataSourceHolder {
    private CustomerDataSourceHolder(){
    }

    /**
     * 上下文
     */
    private static final ThreadLocal<String> CONTEXT_KEY = new ThreadLocal<>();

    /**
     * 设置当前DataSource
     *
     * @param key the key
     */
    public static void setKey(String key) {
        CONTEXT_KEY.set(key);
    }

    /**
     * 获取当前DataSource
     *
     * @return data source key
     */
    public static String getKey() {
        return CONTEXT_KEY.get();
    }

    /**
     * 清除当前DataSource
     */
    public static void clearKey() {
        CONTEXT_KEY.remove();
    }
}
