package com.carbybus.cove.domain.view;

import lombok.Data;

@Data
public class UserLoginOutput {
    private String token;
    private Integer expire;
}
