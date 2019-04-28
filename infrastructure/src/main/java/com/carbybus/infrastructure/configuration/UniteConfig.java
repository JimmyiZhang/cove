package com.carbybus.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 统一配置
 *
 * @author jimmy.zhang
 * @date 2019-04-28
 */
@Data
@Configuration
@ConfigurationProperties("summer")
public class UniteConfig {
    /**
     * http配置
     */

    private UniteHttpConfig httpConfig;

    /**
     * jwt配置
     */
    private UniteJwtConfig jwtConfig;
}
