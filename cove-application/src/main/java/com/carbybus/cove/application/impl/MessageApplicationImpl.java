package com.carbybus.cove.application.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.carbybus.cove.application.MessageApplication;
import com.carbybus.cove.domain.entity.Message;
import com.carbybus.cove.repository.CompanyTrailerLoadingRepository;
import com.carbybus.cove.repository.MessageRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 消息应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
@CacheConfig(cacheNames = {"message"})
public class MessageApplicationImpl extends DefaultApplication<MessageRepository, Message> implements MessageApplication {
    @Autowired
    private CompanyTrailerLoadingRepository loadingRep;

    @Override
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

    @Override
    @Cacheable(key = "#id")
    public Message findById(Long id) {
        Message message = Message.create(10, 1);

        System.out.println("从存储中获取数据");
        return message;
    }

    @Override
    @CachePut(key = "#id")
    public Message updateById(Long id) {
        Message message = Message.create(20, 2);

        System.out.println("从存储中更新数据");
        return message;
    }

    @Override
    @CacheEvict(key = "#id")
    public Message deleteById(Long id) {
        Message message = Message.create(20, 2);

        System.out.println("从存储中更新数据");
        return message;
    }

    @Override
    @Cacheable(key = "#name")
    public Message findByName(String name) {
        System.out.println("从存储中更新数据");
        return Message.create(1, 1);
    }

    @Override
    @Cacheable(value = "message", key = "#name")
    public Message findByName1(String name) {
        System.out.println("从存储中更新数据");
        return Message.create(1, 1);
    }

    @Override
    @Cacheable(value = "message#100", key = "#name")
    public Message findByName2(String name) {
        System.out.println("从存储中更新数据");
        return Message.create(1, 1);
    }

    @Override
    public Message getOne(){
        Message message = Message.create(1,1);
        return message;
    }

    @Override
    public List<Message> listAll(){
        System.out.println("从存储中获取所有数据");
        List<Message> messages = new ArrayList<>();

        messages.add(Message.create(1,1));
        messages.add(Message.create(1,2));
        messages.add(Message.create(1,3));
        messages.add(Message.create(1,4));

        return messages;
    }
}
