package plus.cove.infrastructure.component;

import plus.cove.infrastructure.exception.BusinessError;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

/**
 * 操作结果
 * <p>
 * 业务的返回，包括
 * 1 非正常输入
 * 2 业务逻辑错误
 * 3 其他系统捕获错误
 * <p>
 * 针对调用成功返回，但结果未知的情况，code=0，业务数据各自返回
 * <p>
 * 前台获取后，根据code进行判断是否成功
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
    private ActionResult() {
    }

    /**
     * 全参构造函数
     *
     * @param code
     * @param message
     */
    private ActionResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 默认的静态函数
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static ActionResult result() {
        return new ActionResult();
    }

    /**
     * 成功的静态方法
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-19
     */
    public static ActionResult success() {
        return new ActionResult(0, "ok");
    }

    /**
     * 失败的静态方法
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static ActionResult failure(BusinessError error) {
        return new ActionResult(error.getCode(), error.getMessage());
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
    public ActionResult succeed(T data) {
        this.code = 0;
        this.message = "ok";
        this.data = data;
        return this;
    }

    /**
     * 成功
     *
     * @author jimmy.zhang
     * @date 2018/6/26
     */
    public ActionResult succeed() {
        this.succeed(null);
        return this;
    }

    /**
     * 失败
     *
     * @param error 错误枚举
     * @author jimmy.zhang
     * @date 2019-03-28
     */
    public ActionResult fail(BusinessError error) {
        this.code = error.getCode();
        this.message = error.getMessage();
        return this;
    }

    /**
     * 失败
     *
     * @param error 错误枚举
     * @param data  错误数据
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public ActionResult<T> fail(BusinessError error, T data) {
        this.fail(error);
        this.data = data;
        return this;
    }

    /**
     * 失败
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public ActionResult fail(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    /**
     * 消息
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public ActionResult message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 数据
     * @param data
     * @return
     */
    public ActionResult data(T data) {
        this.data = data;
        return this;
    }
}
