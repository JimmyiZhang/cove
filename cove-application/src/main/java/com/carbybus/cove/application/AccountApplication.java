package com.carbybus.cove.application;

import com.carbybus.cove.domain.entity.company.Account;
import com.carbybus.cove.domain.view.UserLoginInput;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.BaseApplication;

/**
 * 账号应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface AccountApplication extends BaseApplication<Account> {
    /**
     * 创建账号
     *
     * @param account 账号实体
     * @return 创建账号的结果
     * @author jimmy.zhang
     * @date 2019-03-28
     */
    ActionResult create(Account account);

    /**
     * 根据名称获取账号
     *
     * @param name 账号名称
     * @return 对应名称的账号实体，没有返回 null
     * @author Liuyoushi
     * @date 2019-04-08
     */
    Account getByName(String name);

    /**
    * 登录，并返回认证token
    * @param
    * @return
    * @author jimmy.zhang
    * @date 2019-05-14
    */
    ActionResult login(UserLoginInput input);
}
