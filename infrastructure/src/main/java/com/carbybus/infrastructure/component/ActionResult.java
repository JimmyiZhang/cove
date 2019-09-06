package com.carbybus.infrastructure.component;

import com.carbybus.infrastructure.exception.BusinessError;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * 操作返回结果
 * </p>
 * <p>
 * 适用于保存，更新，删除操作
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@ToString
public class ActionResult<T> {
    @Getter
    private int code;

    @Getter
    private String message;

    @Getter
    private T data;

    /**
     * 默认构造函数
     *
     * @author jimmy.zhang
     * @date 2019-04-03
     */
    public ActionResult() {
    }

    /**
     * 构造函数
     *
     * @param error 错误枚举
     * @author Liuyoushi
     * @date 2019-04-08
     */
    public ActionResult(BusinessError error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    /**
     * 构造函数
     *
     * @param code    编码
     * @param message 消息
     * @author jimmy.zhang
     * @date 2019-04-03
     */
    ActionResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功的静态方法
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-19
     */
    public static ActionResult success() {
        return new ActionResult(0, "ok");
    }

    /**
     * 成功的静态方法
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-05
     */
    public static <T> ActionResult success(T data) {
        ActionResult result = ActionResult.success();
        result.succeed(data);
        return result;
    }

    /**
     * 失败的静态方法
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-05
     */
    public static ActionResult failure(BusinessError error) {
        ActionResult result = new ActionResult();
        result.fail(error);
        return result;
    }

    /**
     * 是否成功
     *
     * @return true-操作成功
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.code == 0;
    }

    @JsonIgnore
    public boolean noSuccess() {
        return !this.isSuccess();
    }


    /**
     * 成功
     *
     * @param data 成功返回数据
     * @author jimmy.zhang
     * @date 2018/6/26
     */
    public void succeed(T data) {
        this.code = 0;
        this.message = "ok";
        this.data = data;
    }

    /**
     * 成功
     *
     * @author jimmy.zhang
     * @date 2018/6/26
     */
    public void succeed() {
        this.succeed(null);
    }

    /**
     * 失败
     *
     * @param error 错误枚举
     * @author jimmy.zhang
     * @date 2019-03-28
     */
    public void fail(BusinessError error) {
        this.fail(error.getCode(), error.getMessage(), null);
    }

    /**
     * 失败
     *
     * @param error 错误枚举
     * @param data  错误数据
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public void fail(BusinessError error, T data) {
        this.fail(error.getCode(), error.getMessage(), data);
    }

    /**
     * 失败
     *
     * @param code    错误码
     * @param message 错误消息
     * @author jimmy.zhang
     * @date 2018/6/26
     */
    public void fail(int code, String message) {
        this.fail(code, message, null);
    }

    /**
     * 失败
     *
     * @param code    错误码
     * @param message 错误消息
     * @param data    错误数据
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public void fail(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
