package com.carbybus.infrastructure.test;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.infrastructure.caching.CacheUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class CacheUtilsTest {
    @Autowired
    CacheProperties property;

    @Autowired
    CacheUtils utils;

    @Test
    public void manager() {
        System.out.println(property.getType().toString());
        Assert.isTrue(property.getType().equals(CacheType.SIMPLE), "缓存配置");
    }

    @Test
    public void cache() {
        utils.put("TEST", "TEST", "OK");
        Object obj = utils.get("TEST", "TEST");
        Assert.notNull(obj, "缓存获取测试");
    }

    @Test
    public void getNull() {
        Object obj = utils.get("TEST", "NULL");
        Assert.isNull(obj, "缓存获取测试");
    }

    @Test
    public void getTypeNull() {
        CacheUtils obj = utils.get("TEST", "NULL", CacheUtils.class);
        Assert.isNull(obj, "缓存获取测试");
    }
}
