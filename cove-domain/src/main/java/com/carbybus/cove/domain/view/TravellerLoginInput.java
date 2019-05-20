package com.carbybus.cove.domain.view;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * 用户登录输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
public class TravellerLoginInput {
    @NotEmpty(message = "账号不能为空")
    @Size(min = 4, max = 32, message = "账号5-32个字符")
    private String email;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 4, max = 32, message = "密码5-32个字符")
    private String password;
}
