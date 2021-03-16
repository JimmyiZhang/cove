package plus.cove.jazzy.application;

import plus.cove.infrastructure.component.ActionResult;
import plus.cove.jazzy.domain.view.CommentCreateInput;

/**
 * 消息应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface CommentApplication {
    /**
     * 创建
     *
     * @param comment 实体
     * @return 创建结果
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    ActionResult create(CommentCreateInput comment);
}
