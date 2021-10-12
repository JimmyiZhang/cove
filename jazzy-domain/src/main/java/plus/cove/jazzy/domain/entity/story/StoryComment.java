package plus.cove.jazzy.domain.entity.story;

import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultEntity;
import plus.cove.jazzy.domain.principal.UserPrincipal;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 评论
 *
 * @author Jimmy.Zhang
 */
@Data
@Entity
@Table(name = "story_comment")
@EqualsAndHashCode(callSuper = true)
public class StoryComment extends DefaultEntity {
    private Long storyId;
    private Long userId;
    private String comment;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    public static StoryComment create(Story story, UserPrincipal user, String content) {
        StoryComment entity = new StoryComment();
        entity.valueOf();
        entity.storyId = story.getId();
        entity.userId = user.getUserId();
        entity.createTime = LocalDateTime.now();
        entity.updateTime = entity.createTime;
        entity.comment = content;
        return entity;
    }
}