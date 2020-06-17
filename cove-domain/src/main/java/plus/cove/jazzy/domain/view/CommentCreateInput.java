package plus.cove.jazzy.domain.view;

import lombok.Data;
import plus.cove.jazzy.domain.entity.comment.Comment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 评论输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
public class CommentCreateInput {
    @NotNull(message = "评论不能为空")
    @Size(max = 120, message = "最多120个字")
    private String comment;

    @NotNull(message = "故事不能为空")
    @Min(value = 0, message = "故事编码无效")
    private Long storyId;

    /**
     * 转化
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-24
     */
    public Comment Convert() {
        return null;
    }
}
