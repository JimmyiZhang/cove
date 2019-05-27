package com.carbybus.cove.test.application;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.application.StoryApplication;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.domain.view.StoryCreateInput;
import com.carbybus.infrastructure.component.ActionResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class StoryApplicationTest {
    @Autowired
    private StoryApplication storyApp;

    @Test
    public void create() {
        UserPrincipal user = UserPrincipal.init();

        StoryCreateInput input = new StoryCreateInput();
        input.setSubject("文化之旅")
                .setUrl("http://www.carbycar.com.cn")
                .setDescription("六国古都")
                .setLatitude(32.0849016635)
                .setLongitude(118.7848663330)
                .setTakeTime(LocalDateTime.now());

        ActionResult result = storyApp.create(input, user);
        Assert.isTrue(result.isSuccess(), "创建成功");
    }


}
