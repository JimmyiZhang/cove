package com.carbybus.infrastructure.jwt;

import lombok.Data;

/**
 * jwt输入结果
 * 包括token和过期时间
 *
 * @author jimmy.zhang
 * @date 2019-05-15
 */
@Data
public class JwtResult {
    private String token;
    private Integer expire;
}
