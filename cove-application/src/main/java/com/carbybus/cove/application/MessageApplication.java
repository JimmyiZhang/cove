package com.carbybus.cove.application;

import com.carbybus.cove.domain.entity.Message;
import com.carbybus.cove.domain.entity.company.Company;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.BaseApplication;

/**
 * 消息应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface MessageApplication extends BaseApplication<Message> {
    /**
     * 创建
     *
     * @param message 实体
     * @return 创建结果
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    ActionResult create(Message message);
}
