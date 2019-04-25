package com.carbybus.infrastructure.exception;

/**
 * <p>
 * 业务错误枚举
 * </p>
 * 所有异常枚举接口
 *
 * @author jimmy.zhang
 * @date 2019-02-28
 */
public interface BusinessError {
    /**
     * 获取描述
     *
     * @return 错误描述
     * @author jimmy.zhang
     * @date 2019-03-29
     */
    String getMessage();

    /**
     * 获取编码
     *
     * @return 错误编码
     * @author jimmy.zhang
     * @date 2019-03-29
     */
    int getCode();
}
