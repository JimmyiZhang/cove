package com.carbybus.cove.test.redis;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.domain.entity.Message;
import com.carbybus.infrastructure.redis.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class RedisUtilsTest {
    @Autowired
    RedisUtils redisUtils;

    @Test
    public void setRedis() {
        boolean ok = redisUtils.set("RR", "OK");
        Assert.isTrue(ok, "redis set");

        LocalDateTime expiredTime = LocalDateTime.now().plusMinutes(5);
        String expiredString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:Ss").format(expiredTime);

        boolean ok1 = redisUtils.set("RR-5", expiredString, 5L);
        Assert.isTrue(ok1, "redis set");
    }

    @Test
    public void setRedisObject() {
        Message message = Message.create(1, 1);
        boolean ok = redisUtils.set("MM", message);
        Assert.isTrue(ok, "redis object set");
    }

    @Test
    public void getRedisObject() {
        Message message = redisUtils.get("MM", Message.class);
        System.out.println(message);
        Assert.notNull(message, "redis object set");
    }
}
