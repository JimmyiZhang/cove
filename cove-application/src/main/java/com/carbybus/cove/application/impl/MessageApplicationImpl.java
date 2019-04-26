package com.carbybus.cove.application.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.carbybus.cove.application.MessageApplication;
import com.carbybus.cove.domain.entity.Message;
import com.carbybus.cove.domain.exception.MessageError;
import com.carbybus.cove.repository.CompanyTrailerLoadingRepository;
import com.carbybus.cove.repository.MessageRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import com.carbybus.infrastructure.sharding.MasterOnlySharding;
import com.carbybus.infrastructure.utils.IntegerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 公司应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class MessageApplicationImpl extends DefaultApplication<MessageRepository, Message> implements MessageApplication {
    @Autowired
    private CompanyTrailerLoadingRepository loadingRep;

    @Override
    @MasterOnlySharding
    public ActionResult create(Message message) {
        ActionResult result = new ActionResult();

        Wrapper<Message> condition = new LambdaQueryWrapper<Message>()
                .eq(Message::getResourceId, message.getResourceId());

        // 检查数据
        this.repository.selectCount(condition);
        // 保存数据
        this.repository.insert(message);
        result.succeed();
        return result;
    }
}
