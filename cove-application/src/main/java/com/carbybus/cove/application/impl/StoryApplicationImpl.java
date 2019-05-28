package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.StoryApplication;
import com.carbybus.cove.domain.converter.StoryConverter;
import com.carbybus.cove.domain.entity.coordinate.CoordinateAround;
import com.carbybus.cove.domain.entity.journey.Story;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.domain.view.StoryCreateInput;
import com.carbybus.cove.domain.view.StoryViewOutput;
import com.carbybus.cove.repository.StoryRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import com.carbybus.infrastructure.generator.KeyGeneratorBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 照片应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class StoryApplicationImpl extends DefaultApplication<StoryRepository, Story> implements StoryApplication {
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ActionResult create(StoryCreateInput input, UserPrincipal owner) {
        ActionResult result = ActionResult.OK;

        Story story = StoryConverter.INSTANCE.convertFrom(input, owner);
        long id = KeyGeneratorBuilder.INSTANCE.build();
        story.setId(id);

        this.repository.insert(story);
        return result;
    }

    @Override
    public List<StoryViewOutput> listByName(String name) {
        List<Story> stories = repository.selectByName(name, 100);
        return StoryConverter.INSTANCE.convertAllTo(stories);
    }

    @Override
    public List<StoryViewOutput> listByNear(CoordinateAround around) {
        List<Story> stories = repository.selectByNear(around, 100);
        return StoryConverter.INSTANCE.convertAllTo(stories);
    }

    @Override
    public List<StoryViewOutput> listBySubject(String[] subjects) {
        List<Story> stories = repository.selectBySubject(subjects, 100);
        return StoryConverter.INSTANCE.convertAllTo(stories);
    }
}
