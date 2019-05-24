package com.carbybus.cove.domain.entity.comment;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.journey.Story;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 评论
 *
 * @author Jimmy.Zhang
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "comment")
public class Comment extends DefaultEntity {
    private Long storyId;
    private Long ownerId;
    private String comment;
    private LocalDateTime createTime;

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    public static Comment create(Story story, UserPrincipal user, String content) {
        Comment comment = new Comment();
        comment.valueOf();
        comment.setStoryId(story.getId())
                .setOwnerId(user.getUserId())
                .setCreateTime(LocalDateTime.now())
                .setComment(content);

        return comment;
    }
}