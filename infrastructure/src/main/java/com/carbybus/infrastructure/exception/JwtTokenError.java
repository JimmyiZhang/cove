package com.carbybus.infrastructure.exception;

/**
 * 验证错误
 * 20-29
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum JwtTokenError implements BusinessError {
    CREATION_EXCEPTION(70, "生成TOKEN异常"),
    DECODE_EXCEPTION(71, "解码TOKEN异常"),
    VERIFICATION_EXCEPTION(72, "验证TOKEN异常"),
    INVALID_SECRET(73, "无效的TOKEN秘钥");

    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;

    /**
     * @description: 构造函数
     * @param:
     * @return:
     * @author: jimmy.zhang
     * @date: 2019-03-08
     */
    JwtTokenError(final int code, final String message) {
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

