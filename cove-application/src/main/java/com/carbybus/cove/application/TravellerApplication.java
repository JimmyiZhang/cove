package com.carbybus.cove.application;


import com.carbybus.cove.domain.entity.account.Traveller;
import com.carbybus.cove.domain.view.TravellerLoginInput;
import com.carbybus.cove.domain.view.TravellerSignupInput;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.BaseApplication;

/**
 * 账号应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface TravellerApplication extends BaseApplication<Traveller> {
    /**
     * 注册
     *
     * @param input 注册数据
     * @return 注册结果
     * @author jimmy.zhang
     * @date 2019-03-28
     */
    ActionResult signup(TravellerSignupInput input);

    /**
     * 登录
     *
     * @param input 登录数据
     * @return 登录结果，含token
     * @author jimmy.zhang
     * @date 2019-05-14
     */
    ActionResult login(TravellerLoginInput input);
}