package com.carbybus.cove.test.application;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.application.StoryApplication;
import com.carbybus.cove.domain.entity.coordinate.CoordinateAround;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.domain.view.StoryCreateInput;
import com.carbybus.cove.domain.view.StoryViewOutput;
import com.carbybus.infrastructure.component.ActionResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class StoryApplicationTest {
    @Autowired
    private StoryApplication storyApp;

    @Test
    public void create() {
        UserPrincipal user = UserPrincipal.init(1L,"jimmy");

        StoryCreateInput input = new StoryCreateInput();
        input.setSubject("文化之旅")
                .setName("文化之旅")
                .setUrl("http://www.carbycar.com.cn")
                .setDescription("六国古都")
                .setLatitude(32.0849016635)
                .setLongitude(118.7848663330)
                .setTakeTime(LocalDateTime.now());

        ActionResult result = storyApp.create(input, user);
        Assert.isTrue(result.isSuccess(), "创建成功");
    }

    @Test
    public void listByName() {
        String name = "古都";
        List<StoryViewOutput> views = storyApp.listByName(name);

        for (StoryViewOutput view : views) {
            System.out.println(view);
        }
        Assert.isTrue(views.size() > 0, "获取测试");
    }

    @Test
    public void listByNullName() {
        String name = null;
        List<StoryViewOutput> views = storyApp.listByName(name);

        for (StoryViewOutput view : views) {
            System.out.println(view);
        }
        Assert.isTrue(views.size() > 0, "获取测试");
    }

    @Test
    public void listByNear() {
        CoordinateAround around = new CoordinateAround(
                31.08, 110.78,
                43.18, 119.78);
        List<StoryViewOutput> views = storyApp.listByNear(around);

        for (StoryViewOutput view : views) {
            System.out.println(view);
        }
        Assert.isTrue(views.size() > 0, "获取测试");
    }

    @Test
    public void listBySubject() {
        String[] subjects = new String[]{"文化之旅","吃货"};
        List<StoryViewOutput> views = storyApp.listBySubject(subjects);

        for (StoryViewOutput view : views) {
            System.out.println(view);
        }
        Assert.isTrue(views.size() > 0, "获取测试");
    }
}
