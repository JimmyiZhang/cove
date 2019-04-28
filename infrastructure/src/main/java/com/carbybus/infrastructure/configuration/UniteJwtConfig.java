package com.carbybus.infrastructure.configuration;

import lombok.Data;

/**
 * jwt配置
 *
 * @author jimmy.zhang
 * @ 2019-04-28
 */
@Data
public class UniteJwtConfig {
    /**
     * Token过期时间，单位分钟
     * 默认72小时
     */
    private int tokenExpired = 72 * 60;

    /**
     * Token秘钥
     */
    private String tokenSecret;

    /**
     * Token主题
     */
    private String tokenSubject = "sub";

    /**
     * Token发行人
     */
    private String tokenIssue = "iss";

    /**
     * Token身份
     */
    private String tokenClaim = "id";
}
