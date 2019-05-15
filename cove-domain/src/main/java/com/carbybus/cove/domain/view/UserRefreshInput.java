package com.carbybus.cove.domain.view;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRefreshInput {
    @NotEmpty(message = "token不能为空")
    private String token;
}
