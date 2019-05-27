package com.carbybus.cove.domain.converter;

import com.carbybus.cove.domain.entity.journey.Story;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.domain.view.StoryCreateInput;
import com.carbybus.infrastructure.converter.DefaultConverter;

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
    }

    public Story convertFrom(StoryCreateInput input, UserPrincipal user) {
        Story out = super.convert(input, Story.class);
        out.setOwnerId(user.getUserId());
        return out;
    }
}
