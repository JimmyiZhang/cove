package utils;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.email.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class EmailUtilsTest {
    @Autowired
    private EmailUtils utils;

    @Test
    public void send() {
        ActionResult result = utils.build()
                .to("43587036@qq.com")
                .subject("test")
                .message("test")
                .send();

        Assert.isTrue(result.isSuccess(), "send email");
    }
}
