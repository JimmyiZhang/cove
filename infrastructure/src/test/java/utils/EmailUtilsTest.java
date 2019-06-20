package utils;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.email.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.carbybus.infrastructure.email")
@ContextConfiguration(locations = {"classpath:application.xml"})
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
