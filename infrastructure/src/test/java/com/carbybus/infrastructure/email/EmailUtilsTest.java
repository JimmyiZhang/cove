package com.carbybus.infrastructure.email;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.email.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JavaMailSender.class, MailSenderAutoConfiguration.class, EmailUtils.class},
        initializers = ConfigFileApplicationContextInitializer.class)
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
