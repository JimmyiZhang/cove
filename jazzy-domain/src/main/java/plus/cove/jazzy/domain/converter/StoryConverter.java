package plus.cove.jazzy.domain.converter;

import plus.cove.infrastructure.converter.DefaultConverter;
import plus.cove.jazzy.domain.entity.story.Story;
import plus.cove.jazzy.domain.principal.UserPrincipal;
import plus.cove.jazzy.domain.view.StoryCreateInput;
import plus.cove.jazzy.domain.view.StoryViewOutput;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 照片转换器
 *
 * @author jimmy.zhang
 * @date 2019/5/20
 */
public class StoryConverter extends DefaultConverter {
    public static final StoryConverter INSTANCE = new StoryConverter();

    private StoryConverter() {
        super();
        MAPPER_FACTORY.classMap(StoryCreateInput.class, Story.class)
                .field("latitude", "location.latitude")
                .field("longitude", "location.longitude")
                .byDefault()
                .register();

        MAPPER_FACTORY.classMap(Story.class, StoryViewOutput.class)
                .field("location.latitude", "latitude")
                .field("location.longitude", "longitude")
                .byDefault()
                .register();
    }

    public Story convertFrom(StoryCreateInput input, UserPrincipal user) {
        Story out = super.convert(input, Story.class);
        out.setUserId(user.getUserId());
        LocalDateTime now = LocalDateTime.now();
        out.setCreateTime(now);
        out.setUpdateTime(now);
        return out;
    }

    public StoryViewOutput convertTo(Story story) {
        StoryViewOutput out = super.convert(story, StoryViewOutput.class);
        return out;
    }

    public List<StoryViewOutput> convertAllTo(List<Story> stories) {
        List<StoryViewOutput> views = new ArrayList<>();

        if (stories == null) {
            return views;
        }

        for (Story story : stories) {
            StoryViewOutput view = StoryConverter.INSTANCE.convertTo(story);
            views.add(view);
        }
        return views;
    }
}
