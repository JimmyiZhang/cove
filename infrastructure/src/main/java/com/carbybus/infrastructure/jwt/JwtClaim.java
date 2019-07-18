package com.carbybus.infrastructure.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * jwt输出结果
 * 包括是否成功和身份
 *
 * @author jimmy.zhang
 * @date 2019-05-15
 */
@AllArgsConstructor
public class JwtClaim {
    @Getter
    private Boolean success;

    @Getter
    private String claim;
}
