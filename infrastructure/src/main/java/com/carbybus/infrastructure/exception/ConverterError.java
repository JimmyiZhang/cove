package com.carbybus.infrastructure.exception;

/**
 * 验证错误
 * 20-29
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum ConverterError implements BusinessError {
    INVALID_ORIGIN(20, "无效的来源数据"),
    INVALID_TARGET(21, "无效的目标数据"),
    INVALID_CLASS_TYPE(22, "无效的类型类别");

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
    ConverterError(final int code, final String message) {
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

