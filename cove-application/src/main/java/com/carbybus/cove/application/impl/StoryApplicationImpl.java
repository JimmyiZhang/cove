package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.StoryApplication;
import com.carbybus.cove.domain.entity.journey.Story;
import com.carbybus.cove.domain.view.StoryCreateInput;
import com.carbybus.cove.repository.StoryRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public ActionResult create(StoryCreateInput input) {
        ActionResult result = ActionResult.OK;

        return result;
    }
}
