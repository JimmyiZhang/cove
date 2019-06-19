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
import org.springframework.util.Assert;

@RunWith(MockitoJUnitRunner.class)
public class EmailUtilsTest {
    @Mock
    private UniteEmailConfig config = new UniteEmailConfig();

    @Autowired
    private EmailUtils utils;

    @Before
    public void before() {
        utils = Mockito.mock(EmailUtils.class);
    }

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
