package plus.cove.jazzy.application.impl;

import plus.cove.infrastructure.component.ActionResult;
import plus.cove.jazzy.application.CommentApplication;
import plus.cove.jazzy.domain.entity.story.StoryComment;
import plus.cove.jazzy.domain.view.CommentCreateInput;
import plus.cove.jazzy.repository.StoryCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class CommentApplicationImpl implements CommentApplication {
    @Autowired
    private StoryCommentRepository commentRep;

    @Override
    public ActionResult create(CommentCreateInput input) {
        ActionResult result = ActionResult.success();

        StoryComment storyComment = input.Convert();
        // 保存数据
        this.commentRep.insert(storyComment);
        return result;
    }

}
