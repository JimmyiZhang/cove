package com.carbybus.infrastructure.http;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * http配置
 *
 * @author jimmy.zhang
 * @ 2019-04-28
 */
@Configuration
@ConfigurationProperties("summer.http-config")
public class UniteHttpConfig {
    /**
     * 分隔符
     */
    private static final String SEPARATOR_ALLOWED = ",";

    /**
     * 全部允许
     */
    private static final String WHOLE_ALLOWED = "*";

    /**
     * 缓存时间，默认10分钟
     */
    @Getter
    @Setter
    private long cacheMaxAge = 10 * 60L;

    /**
     * 跨域预检有效期，默认1天，单位秒
     */
    @Getter
    @Setter
    private long corsMaxAge = 24 * 60 * 60L;

    /**
     * 跨域允许的源地址
     * 不含http://
     */
    @Setter
    private String corsAllowedOrigins;

    /**
     * 获取跨域请求域
     * 包括协议
     *
     * @param
     * @return 跨域请求域数组，不设置则为*
     * @author jimmy.zhang
     * @date 2019-04-28
     */
    public String[] getCorsAllowedOrigins() {
        if (StringUtils.isEmpty(corsAllowedOrigins) || WHOLE_ALLOWED.equals(corsAllowedOrigins)) {
            return new String[]{WHOLE_ALLOWED};
        }

        return corsAllowedOrigins.split(SEPARATOR_ALLOWED);
    }

    /**
     * 跨域允许的头
     */
    @Setter
    private String corsAllowedHeaders;

    /**
     * 获取跨域请求头
     *
     * @param
     * @return 跨域请求头数组，不设置则为*
     * @author jimmy.zhang
     * @date 2019-04-28
     */
    public String[] getCorsAllowedHeaders() {
        if (StringUtils.isEmpty(corsAllowedHeaders) || WHOLE_ALLOWED.equals(corsAllowedHeaders)) {
            return new String[]{WHOLE_ALLOWED};
        }

        return corsAllowedHeaders.split(SEPARATOR_ALLOWED);
    }

    @Setter
    private String corsAllowedMethods;

    /**
     * 获取跨域请求方法
     *
     * @param
     * @return 跨域请求头数组，不设置则为*
     * @author jimmy.zhang
     * @date 2019-04-28
     */
    public String[] getCorsAllowedMethods() {
        if (StringUtils.isEmpty(corsAllowedMethods) || WHOLE_ALLOWED.equals(corsAllowedMethods)) {
            return new String[]{WHOLE_ALLOWED};
        }

        return corsAllowedMethods.split(SEPARATOR_ALLOWED);
    }
}
