package plus.cove.jazzy.api.test.converter;

import plus.cove.jazzy.api.ApiApplication;
import plus.cove.jazzy.domain.converter.StoryConverter;
import plus.cove.jazzy.domain.entity.story.Story;
import plus.cove.jazzy.domain.principal.UserPrincipal;
import plus.cove.jazzy.domain.view.StoryCreateInput;
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
        input.setTakeTime(LocalDateTime.now());
        input.setDescription("TTTTTest");
        input.setLatitude(38.9989752835);
        input.setLongitude(117.6972198486);
        input.setSubject("tianjin");
        input.setName("风花雪月");
        UserPrincipal user = UserPrincipal.init(1L, "jimmy");
        user.setUserId(1L);

        Story entity = StoryConverter.INSTANCE.convertFrom(input, user);
        System.out.println(entity);
        Assert.notNull(entity, "故事转化");
        Assert.notNull(entity.getLocation(), "故事坐标");
    }
}
