package com.carbybus.cove.test.converter;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.domain.converter.StoryConverter;
import com.carbybus.cove.domain.entity.journey.Story;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.domain.view.StoryCreateInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class StoryConverterTest {
    @Test
    public void convert() {
        StoryCreateInput input = new StoryCreateInput();
        input.setTakeTime(LocalDateTime.now())
                .setDescription("TTTTTest")
                .setLatitude(38.9989752835)
                .setLongitude(117.6972198486)
                .setSubject("tianjin");

        UserPrincipal user = UserPrincipal.init();
        user.setUserId(1L);

        Story entity = StoryConverter.INSTANCE.convertFrom(input, user);
        System.out.println(entity);
        Assert.notNull(entity, "故事转化");
        Assert.notNull(entity.getLocation(), "故事坐标");
    }
}
