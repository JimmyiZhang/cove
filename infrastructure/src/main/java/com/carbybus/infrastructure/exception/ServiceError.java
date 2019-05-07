package com.carbybus.infrastructure.exception;

/**
 * 服务错误
 * 20-29
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum ServiceError implements BusinessError {
    BAD_REQUEST(80,"无效权限"),
    NOT_FOUND(81, "无效页面"),
    SERVER_ERROR(82, "内部错误");

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
    ServiceError(final int code, final String message) {
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

