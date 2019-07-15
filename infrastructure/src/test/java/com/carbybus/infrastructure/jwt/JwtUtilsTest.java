package com.carbybus.infrastructure.jwt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UniteJwtConfig.class, JwtUtils.class},
        initializers = ConfigFileApplicationContextInitializer.class)
public class JwtUtilsTest {
    @Autowired
    UniteJwtConfig config;

    @Autowired
    JwtUtils utils;

    @Test
    public void create() {
        System.out.println(config.getTokenSecret());

        JwtResult result = utils.create("Happy End");
        String token = result.getToken();
        System.out.println(token);

        Assert.isTrue(!token.isEmpty(), "jwt创建");
    }
}
