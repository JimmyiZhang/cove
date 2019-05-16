package com.carbybus.cove.api.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus配置
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.carbybus.cove.repository")
public class MyBatisPlusConfig {
    /**
     * SQL自动分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile("develop")
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performance = new PerformanceInterceptor();
        // 最大执行时间10s，超过则停止运行
        performance.setMaxTime(10 * 1000L);
        return performance;
    }
}


