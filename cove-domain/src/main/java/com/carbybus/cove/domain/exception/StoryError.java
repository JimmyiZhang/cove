package com.carbybus.cove.domain.exception;

import com.carbybus.infrastructure.exception.BusinessError;

/**
 * 账号错误
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum StoryError implements BusinessError {
    INVALID_COORDINATE(10, "图片中无有效的坐标信息");

    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;

    private static final int CATEGORY = 3000;

    /**
     * 构造函数
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-03-08
     */
    StoryError(final int code, final String message) {
        this.code = CATEGORY + code;
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
