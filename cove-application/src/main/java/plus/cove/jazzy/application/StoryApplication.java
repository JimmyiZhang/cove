package plus.cove.jazzy.application;


import plus.cove.infrastructure.component.ActionResult;
import plus.cove.jazzy.domain.entity.coordinate.CoordinateAround;
import plus.cove.jazzy.domain.entity.story.Story;
import plus.cove.jazzy.domain.principal.UserPrincipal;
import plus.cove.jazzy.domain.view.StoryCreateInput;
import plus.cove.jazzy.domain.view.StoryViewOutput;

import java.util.List;

/**
 * 照片应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface StoryApplication {
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
     * 保存
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    ActionResult save(Story story);

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
    List<StoryViewOutput> listBySubject(String[] subjects);
}
