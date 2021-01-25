package plus.cove.jazzy.api.test.repository;

import plus.cove.jazzy.api.ApiApplication;
import plus.cove.jazzy.domain.entity.coordinate.Coordinate;
import plus.cove.jazzy.domain.entity.story.Story;
import plus.cove.jazzy.domain.principal.UserPrincipal;
import plus.cove.jazzy.repository.StoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class StoryRepositoryTest {
    @Autowired
    private StoryRepository storyRep;

    @Test
    public void insert() {
        UserPrincipal user = UserPrincipal.init(1L, "jimmy");
        user.setUserId(1L);

        Coordinate location = new Coordinate(38.9989752835, 117.6972198486);
        Story story = Story.create(user);
        story.setDescription("TTTTTTest");
        story.setLocation(location);
        story.setSubject("tianjin");
        story.setName("风花雪月");
        story.setDescription("DDDDescription");
        story.setImage("https://cove-1259284616.cos.ap-beijing.myqcloud.com/photos/IMG_1325.JPG?q-sign-algorithm=sha1&q-ak=AKIDOFjQJX6VMcNpCs4xtjBG9uItrLx7RPgi&q-sign-time=1558501623;1558503423&q-key-time=1558501623;1558503423&q-header-list=&q-url-param-list=&q-signature=eecb541e4fbc6d7af0eb4ed2ba8944bc0bedbb49&x-cos-security-token=cda0887df6238b19bbe0e77e3a62e41996bb74d610001");
        story.setTakeTime(LocalDateTime.now());

        storyRep.insertStory(story);
        Assert.isTrue(true, "照片插入");
    }

    @Test
    public void selectPage(){

    }
}
