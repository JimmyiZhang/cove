package com.carbybus.cove.test.application;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.application.TravellerApplication;
import com.carbybus.cove.domain.view.UserLoginInput;
import com.carbybus.cove.domain.view.UserLoginOutput;
import com.carbybus.cove.domain.view.UserSignupInput;
import com.carbybus.infrastructure.component.ActionResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class TravellerApplicationTest {
    @Autowired
    private TravellerApplication travellerApp;

    @Test
    public void singup() {
        UserSignupInput input = new UserSignupInput();
        input.setAccount("zjm281@163.com")
                .setName("jimmy")
                .setPassword("123456");

        ActionResult result = travellerApp.signup(input);
        Assert.isTrue(result.isSuccess(), "注册用户");
    }

    @Test
    public void login() {
        UserLoginInput input = new UserLoginInput();
        input.setName("zjm281@163.com")
                .setPassword("123456");

        ActionResult<UserLoginOutput> output = travellerApp.login(input);

        Assert.isTrue(output.isSuccess(), "登录用户");
        System.out.println("TOKEN:" + output.getData().getToken());
    }
}
