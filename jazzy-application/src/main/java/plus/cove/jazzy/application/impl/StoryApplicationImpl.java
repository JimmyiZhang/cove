package plus.cove.jazzy.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.jazzy.application.StoryApplication;
import plus.cove.jazzy.domain.converter.StoryConverter;
import plus.cove.jazzy.domain.entity.coordinate.CoordinateAround;
import plus.cove.jazzy.domain.entity.story.Story;
import plus.cove.jazzy.domain.principal.UserPrincipal;
import plus.cove.jazzy.domain.view.StoryCreateInput;
import plus.cove.jazzy.domain.view.StoryViewOutput;
import plus.cove.jazzy.repository.StoryRepository;

import java.util.List;


/**
 * 照片应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class StoryApplicationImpl implements StoryApplication {
    @Autowired
    private StoryRepository storyRep;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionResult create(StoryCreateInput input, UserPrincipal owner) {
        ActionResult result = ActionResult.success();

        Story story = StoryConverter.INSTANCE.convertFrom(input, owner);
        story.buildId();

        this.storyRep.insert(story);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionResult save(Story story){
        this.storyRep.insertStory(story);
        return ActionResult.success();
    }

    @Override
    public List<StoryViewOutput> listByName(String name) {
        List<Story> stories = storyRep.selectByName(name, 100);
        return StoryConverter.INSTANCE.convertAllTo(stories);
    }

    @Override
    public List<StoryViewOutput> listByNear(CoordinateAround around) {
        List<Story> stories = storyRep.selectByNear(around, 100);
        return StoryConverter.INSTANCE.convertAllTo(stories);
    }

    @Override
    public List<StoryViewOutput> listBySubject(String[] subjects) {
        List<Story> stories = storyRep.selectBySubject(subjects, 100);
        return StoryConverter.INSTANCE.convertAllTo(stories);
    }
}
