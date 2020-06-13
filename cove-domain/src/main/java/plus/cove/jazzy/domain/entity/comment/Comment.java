package plus.cove.jazzy.domain.entity.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultEntity;
import plus.cove.jazzy.domain.entity.journey.Story;
import plus.cove.jazzy.domain.principal.UserPrincipal;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * 评论
 *
 * @author Jimmy.Zhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Comment extends DefaultEntity {
    private Long storyId;
    private Long ownerId;
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
    public static Comment create(Story story, UserPrincipal user, String content) {
        Comment entity = new Comment();
        entity.valueOf();
        entity.storyId = story.getId();
        entity.ownerId = user.getUserId();
        entity.createTime = LocalDateTime.now();
        entity.updateTime = entity.createTime;
        entity.comment = content;
        return entity;
    }
}