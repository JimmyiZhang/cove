package com.carbybus.cove.domain.exception;

import com.carbybus.infrastructure.exception.BusinessError;

/**
 * 账号错误
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum AccountError implements BusinessError {
    EXISTED_ACCOUNT(10, "该账号已存在"),
    INVALID_NAME(11, "无效的账号"),
    INVALID_PASSWORD(12, "无效的密码");

    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;

    /**
     * 构造函数
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-03-08
     */
    AccountError(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
