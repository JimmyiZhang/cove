package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.PhotoApplication;
import com.carbybus.cove.domain.entity.photograph.Photo;
import com.carbybus.cove.domain.view.PhotoCreateInput;
import com.carbybus.cove.repository.PhotoRepository;
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
public class PhotoApplicationImpl extends DefaultApplication<PhotoRepository, Photo> implements PhotoApplication {
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ActionResult create(PhotoCreateInput input) {
        ActionResult result = ActionResult.OK;

        return result;
    }
}
