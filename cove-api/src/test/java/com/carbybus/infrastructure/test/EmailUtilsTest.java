package com.carbybus.infrastructure.test;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.email.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class EmailUtilsTest {
    @Test
    public void send() {
        ActionResult result = EmailUtils.create()
                .to("43587036@qq.com")
                .subject("test")
                .message("test")
                .send();

        Assert.isTrue(result.isSuccess(), "send email");
    }
}
