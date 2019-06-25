package com.carbybus.cove.domain.view;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 用户激活输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@Accessors(chain = true)
public class UserActiveInput {
    @NotEmpty(message = "激活码不能为空")
    private String code;
}
