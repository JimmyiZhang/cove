package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.MessageApplication;
import com.carbybus.cove.domain.entity.Message;
import com.carbybus.cove.repository.MessageRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 消息应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class MessageApplicationImpl extends DefaultApplication<MessageRepository, Message> implements MessageApplication {
    @Autowired
    private MessageRepository messageRep;

    @Override
    public ActionResult create(Message message) {
        ActionResult result = new ActionResult();

        // 保存数据
        this.repository.insert(message);
        result.succeed();
        return result;
    }

}
