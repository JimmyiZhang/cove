package com.carbybus.cove.domain.view;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRefreshInput {
    @NotEmpty(message = "token不能为空")
    private String token;
}
