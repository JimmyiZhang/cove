package com.carbybus.cove.domain.exception;

import com.carbybus.infrastructure.exception.BusinessError;

/**
 * 消息错误
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum CommentError implements BusinessError {
    LIMITED_COMMENT(10, "受限的评论");

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
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-03-08
     */
    CommentError(final int code, final String message) {
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
