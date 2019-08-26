package com.carbybus.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 统一时间配置
 *
 * @author jimmy.zhang
 * @date 2019-04-29
 */
@Data
@Configuration
@ConfigurationProperties("summer.date-config")
public class UniteDateConfig {

    /**
     * 默认日期时间格式
     */
    private String datetimeFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认日期格式
     */
    private String dateFormat = "yyyy-MM-dd";
}