package com.carbybus.cove.domain.entity.journey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.coordinate.Coordinate;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import com.carbybus.infrastructure.utils.StringConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 照片
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "story")
public class Story extends DefaultEntity {
    private String url;
    private String subject;
    private String description;
    private Coordinate location;
    private LocalDateTime takeTime;
    private LocalDateTime createTime;
    private Long ownerId;

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public static Story create(UserPrincipal user) {
        Story story = new Story();
        story.valueOf();

        story.setCreateTime(LocalDateTime.now())
                .setOwnerId(user.getUserId())
                .setSubject(StringConstants.EMPTY)
                .setDescription(StringConstants.EMPTY);
        return story;
    }
}
