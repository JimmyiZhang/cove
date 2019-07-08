package com.carbybus.infrastructure.jwt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UniteJwtConfig.class)
public class JwtUtilsTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void create() {
        JwtUtils utils = context.getBean(JwtUtils.class);

        JwtResult result = utils.create("1");

        String token = result.getToken();
        System.out.println(token);

        Assert.isTrue(!token.isEmpty(), "jwt创建");
    }
}
