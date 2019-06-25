package com.carbybus.cove.api.component;

import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.exception.BusinessError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    private UserPrincipal user;

    protected UserPrincipal getUser() {
        if (this.user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String claim = auth.getName();
            this.user = UserPrincipal.init().setUserName(claim);
        }

        return this.user;
    }

    /**
     * 成功
     *
     * @param data 需要返回的数据
     * @return 携带返回数据的操作成功结果
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public <T> ActionResult<T> success(T data) {
        ActionResult<T> result = new ActionResult<>();
        result.succeed(data);

        return result;
    }

    /**
     * 成功
     *
     * @return 表示操作成功的结果
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public ActionResult success() {
        ActionResult result = new ActionResult();
        result.succeed();

        return result;
    }

    /**
     * 失败
     *
     * @param error 错误
     * @return 携带错误码和错误消息的操作失败结果
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public ActionResult failure(BusinessError error) {
        ActionResult result = new ActionResult();
        result.fail(error);

        return result;
    }

    /**
     * 失败
     *
     * @param error 错误
     * @param data  返回的数据
     * @return 携带错误码、错误消息和返回数据的操作失败结果
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public <T> ActionResult<T> failure(BusinessError error, T data) {
        ActionResult<T> result = new ActionResult<>();
        result.fail(error, data);

        return result;
    }
}
