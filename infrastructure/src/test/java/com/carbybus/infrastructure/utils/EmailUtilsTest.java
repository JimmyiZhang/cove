package com.carbybus.infrastructure.utils;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.email.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
public class EmailUtilsTest {
    @Autowired
    EmailUtils utils;

    @Test
    public void send() {
        ActionResult result = utils.build()
                .to("43587036@qq.com")
                .subject("test")
                .text("test")
                .send();

        Assert.isTrue(result.isSuccess(), "send email");
    }
}
