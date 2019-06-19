package utils;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.email.EmailUtils;
import com.carbybus.infrastructure.email.UniteEmailConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

public class EmailUtilsTest {
    @Autowired
    private EmailUtils utils;

    @Test
    public void send() {
        ActionResult result = utils
                .to("43587036@qq.com")
                .subject("test")
                .message("test")
                .send();

        Assert.isTrue(result.isSuccess(), "send email");
    }
}
