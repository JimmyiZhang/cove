package plus.cove.jazzy.api.test.application;

import plus.cove.infrastructure.component.ActionResult;
import plus.cove.jazzy.api.ApiApplication;
import plus.cove.jazzy.application.AuthorApplication;
import plus.cove.jazzy.domain.view.UserLoginInput;
import plus.cove.jazzy.domain.view.UserLoginOutput;
import plus.cove.jazzy.domain.view.UserSignupInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class AuthorApplicationTest {
    @Autowired
    private AuthorApplication authorApp;

    @Test
    public void signup() {
        UserSignupInput input = new UserSignupInput();
        input.setAccount("zjm281@163.com");
        input.setName("jimmy");
        input.setPassword("123456");

        ActionResult result = authorApp.signup(input);
        System.out.println(result);
        Assert.isTrue(result.isSuccess(), "注册用户");
    }

    @Test
    public void login() {
        UserLoginInput input = new UserLoginInput();
        input.setName("zjm281@163.com");
        input.setPassword("123456");

        ActionResult<UserLoginOutput> output = authorApp.login(input);
        Assert.isTrue(output.isSuccess(), "登录用户");
        System.out.println("TOKEN:" + output.getData().getToken());
    }
}
