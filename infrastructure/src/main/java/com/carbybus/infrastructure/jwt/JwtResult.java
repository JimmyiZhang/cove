package com.carbybus.infrastructure.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * jwt输入结果
 * 包括token和过期时间
 *
 * @author jimmy.zhang
 * @date 2019-05-15
 */
@AllArgsConstructor
public class JwtResult {
    /**
     * token值
     */
    @Getter
    private String token;

    /**
     * 过期时间，单位分
     */
    @Getter
    private Integer expire;
}
