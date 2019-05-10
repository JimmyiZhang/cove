package com.carbybus.cove.test.caching;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.application.MessageApplication;
import com.carbybus.cove.domain.entity.Message;
import com.carbybus.infrastructure.caching.CacheUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class MessageApplicationTest {
    @Autowired
    MessageApplication messageApp;
    @Autowired
    CacheUtils cacheUtils;

    @Test
    public void getCache() {
        Long id = 1L;
        System.out.println("findById: 注解获取");
        // 注解获取
        Message message1 = messageApp.findById(id);

        System.out.println("findById: 代码获取");
        // 代码获取
        Message message2 = cacheUtils.get("message", id, Message.class);
        Message message3 = messageApp.findById(id);

        Assert.isTrue(message3.equals(message2), "获取缓存");
    }

    @Test
    public void putCache() {
        Long id = 1L;
        System.out.println("findById: 注解获取");
        // 注解获取
        Message message1 = messageApp.findById(id);

        // 更新缓存
        System.out.println("putCache: 更新缓存");
        Message message2 = Message.create(10, 2);
        cacheUtils.put("message", id, message2);

        System.out.println("findById: 注解获取");
        // 注解获取
        Message message3 = messageApp.findById(id);
        System.out.println(message3.getResourceId());

        Assert.isTrue(message2.equals(message3), "更新缓存");
    }

    @Test
    public void evictCache() {
        Long id = 1L;
        System.out.println("findById: 注解获取");
        // 注解获取
        Message message1 = messageApp.findById(id);
        cacheUtils.evict("message", id);

        // 注解获取
        Message message2 = messageApp.findById(id);

        Assert.isTrue(message1.equals(message2), "删除缓存");
    }

    @Test
    public void getCaching() {
        String name = "jimmy";
        System.out.println("findByName: 第一次获取");
        // 注解获取
        Message message1 = messageApp.findByName(name);

        System.out.println("findByName: 第二次获取");
        // 注解获取
        Message message2 = messageApp.findByName(name);
        Assert.isTrue(message1.equals(message2), "自定义Caching");
    }
}
