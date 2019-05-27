package com.carbybus.cove.application;


import com.carbybus.cove.domain.entity.coordinate.CoordinateAround;
import com.carbybus.cove.domain.entity.journey.Story;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.domain.view.StoryCreateInput;
import com.carbybus.cove.domain.view.StoryViewOutput;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.BaseApplication;

import java.util.List;

/**
 * 照片应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface StoryApplication extends BaseApplication<Story> {
    /**
     * 创建
     *
     * @param input 注册数据
     * @param owner 拥有者
     * @return 创建结果
     * @author jimmy.zhang
     * @date 2019-03-28
     */
    ActionResult create(StoryCreateInput input, UserPrincipal owner);

    /**
     * 根据名称查询
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-27
     */
    List<StoryViewOutput> listByName(String name);

    /**
     * 按照范围查询
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-27
     */
    List<StoryViewOutput> listByNear(CoordinateAround around);

    /**
     * 按照主题查询
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-27
     */
    List<StoryViewOutput> listBySubject(String subject);
}
