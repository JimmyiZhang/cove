package com.carbybus.cove.test.application;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.application.TravellerApplication;
import com.carbybus.cove.domain.view.TravellerSignupInput;
import com.carbybus.infrastructure.component.ActionResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class TravellerTest {
    @Autowired
    private TravellerApplication travellerApp;

    @Test
    public void singup() {
        TravellerSignupInput input = new TravellerSignupInput();
        input.setEmail("zjm281@163.com").setName("jimmy");

        ActionResult result = travellerApp.signup(input);
        Assert.isTrue(result.isSuccess(), "注册用户");
    }
}
