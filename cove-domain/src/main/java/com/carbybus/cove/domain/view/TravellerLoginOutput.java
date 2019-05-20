package com.carbybus.cove.domain.view;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户登录输出
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@AllArgsConstructor
public class TravellerLoginOutput {
    private String token;
    private Integer expire;
}
