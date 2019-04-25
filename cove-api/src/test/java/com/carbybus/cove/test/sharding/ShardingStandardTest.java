package com.carbybus.cove.test.sharding;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.domain.entity.Message;
import com.carbybus.cove.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;


// 自定义分片测试
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class ShardingStandardTest {
    @Autowired
    private MessageRepository messageRep;

    // 写分片
    @Test
    public void yearTableWriteStrategy() {
        Message message = Message.create(100, 200);
        System.out.println(message.getCreateTime());

        // master, message_2019
        messageRep.insert(message);
        Assert.notNull(message, "员工保存");
    }

    // 读分片
    @Test
    public void yearTableReadStrategy() {
        Wrapper<Message> condition = new LambdaQueryWrapper<Message>()
                .gt(Message::getId, 1);
        List<Message> messages = messageRep.selectList(condition);
        Assert.isTrue(messages.size() == 0, "消息获取");
    }
}
