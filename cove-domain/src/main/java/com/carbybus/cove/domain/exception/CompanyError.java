package com.carbybus.cove.domain.exception;

import com.carbybus.infrastructure.exception.BusinessError;

/**
 * 公司错误
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum CompanyError implements BusinessError {
    EXISTED_ACCOUNT(10, "该公司已存在");

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
    CompanyError(final int code, final String message) {
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