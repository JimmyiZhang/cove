package plus.cove.jazzy.domain.view;

import plus.cove.jazzy.domain.entity.comment.Comment;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 评论输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
public class CommentCreateInput {
    @NotNull(message = "评论不能为空")
    @Length(max = 120, message = "最多120个字")
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
