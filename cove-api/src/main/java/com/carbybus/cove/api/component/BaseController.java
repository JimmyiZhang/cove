package com.carbybus.cove.api.component;

import com.carbybus.cove.domain.entity.company.EmployeeCategory;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.infrastructure.component.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
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

    protected UserPrincipal user;

    public BaseController() {
        this.user = UserPrincipal.init()
                .setUserCategory(EmployeeCategory.Captain)
                .setUserId(1)
                .setUserName("jimmy")
                .setCompanyId(1);
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
     * @param code    错误码
     * @param message 错误消息
     * @return 携带错误码和错误消息的操作失败结果
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public ActionResult failure(int code, String message) {
        ActionResult result = new ActionResult();
        result.fail(code, message);

        return result;
    }

    /**
     * 失败
     *
     * @param code    错误码
     * @param message 错误消息
     * @param data    返回的数据
     * @return 携带错误码、错误消息和返回数据的操作失败结果
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public <T> ActionResult<T> failure(int code, String message, T data) {
        ActionResult<T> result = new ActionResult<>();
        result.fail(code, message, data);

        return result;
    }

    /**
     * 通过名称获取 Bean
     *
     * @param name Bean 的名称
     * @return 该名称的 Bean 的实例
     * @author Liuyoushi
     * @date 2019/4/8
     */
    protected Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过类型获取 Bean
     *
     * @param clazz Bean 的类型
     * @return 该类型的 Bean 的实例
     * @author Liuyoushi
     * @date 2019/4/8
     */
    protected <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过名称和类型获取 Bean
     *
     * @param name  Bean 的名称
     * @param clazz Bean 的类型
     * @return Bean 的实例
     * @author Liuyoushi
     * @date 2019/4/8
     */
    protected <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    protected ApplicationContext getApplicationContext() {
        ServletContext sc = request.getSession().getServletContext();
        return WebApplicationContextUtils.getWebApplicationContext(sc);
    }
}
