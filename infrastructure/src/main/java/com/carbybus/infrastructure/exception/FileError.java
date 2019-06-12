package com.carbybus.infrastructure.exception;

/**
 * 文件异常
 *
 * @author jimmy.zhang
 * @date 2019-05-31
 */
public enum FileError implements BusinessError {
    NOT_FOUND(61, "文件未找到"),
    CREATE_ERROR(62, "创建失败"),
    READ_ERROR(63, "读取失败");

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
    FileError(final int code, final String message) {
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