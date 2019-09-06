package com.carbybus.cove.api.component;

import com.carbybus.cove.application.UserApplication;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.exception.BusinessError;
import com.carbybus.infrastructure.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    private UserApplication userApp;

    /**
     * 获取当前用户
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-19
     */
    protected UserPrincipal getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String claim = auth.getName();
        log.info("the user authentication is : {}", claim);

        if (auth instanceof AnonymousAuthenticationToken) {
            return UserPrincipal.UNKNOWN;
        } else {
            Long userId = Long.parseLong(claim);

            // 获取用户信息
            UserPrincipal user = userApp.findPrincipal(userId);
            return user;
        }
    }

    /**
     * 获取ip地址
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-19
     */
    protected String getRequestIp() {
        return HttpUtils.getIp(this.request);
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
