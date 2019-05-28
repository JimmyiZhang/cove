package com.carbybus.cove.domain.converter;

import com.carbybus.cove.domain.entity.journey.Story;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.domain.view.StoryCreateInput;
import com.carbybus.cove.domain.view.StoryViewOutput;
import com.carbybus.infrastructure.converter.DefaultConverter;

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
        out.setOwnerId(user.getUserId());
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
