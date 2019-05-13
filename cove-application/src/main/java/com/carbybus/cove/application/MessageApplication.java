package com.carbybus.cove.application;

import com.carbybus.cove.domain.entity.Message;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.BaseApplication;

import java.util.List;

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

    Message findById(Long id);

    Message updateById(Long id);

    Message deleteById(Long id);

    Message findByName(String name);

    Message findByName1(String name);

    Message findByName2(String name);

    Message getOne();

    List<Message> listAll();
}
