package com.carbybus.cove.domain.view;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserLoginInput {
    @NotEmpty(message = "用户名不能为空")
    @Size(min = 4, max = 32, message = "用户名5-32个字符")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 4, max = 32, message = "密码5-32个字符")
    private String password;
}
