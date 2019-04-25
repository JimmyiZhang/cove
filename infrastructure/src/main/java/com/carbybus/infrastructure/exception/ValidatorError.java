package com.carbybus.infrastructure.exception;

/**
 * 验证错误
 * code 范围：10-19
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum ValidatorError implements BusinessError {
    /**
     * 规则
     */
    CONSTRAINT_VIOLATION(10, "无效的验证规则"),

    /**
     * 参数
     */
    METHOD_ARGUMENT(11, "无效的参数");

    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;

    ValidatorError(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

