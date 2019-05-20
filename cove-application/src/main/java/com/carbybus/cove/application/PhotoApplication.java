package com.carbybus.cove.application;


import com.carbybus.cove.domain.entity.photograph.Photo;
import com.carbybus.cove.domain.view.PhotoCreateInput;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.BaseApplication;

/**
 * 照片应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface PhotoApplication extends BaseApplication<Photo> {
    /**
     * 创建
     *
     * @param input 注册数据
     * @return 创建结果
     * @author jimmy.zhang
     * @date 2019-03-28
     */
    ActionResult create(PhotoCreateInput input);
}
