package com.carbybus.cove.domain.entity.journey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.coordinate.Coordinate;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import com.carbybus.infrastructure.file.FileMetadata;
import com.carbybus.infrastructure.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 照片
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "story")
public class Story extends DefaultEntity {
    private String name;
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

        story.setCreateTime(LocalDateTime.now());
        story.setOwnerId(user.getUserId());
        story.setSubject(StringUtils.EMPTY);
        story.setDescription(StringUtils.EMPTY);
        return story;
    }

    /**
     * 从文件创建故事
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-04
     */
    public static Story fromFile(UserPrincipal user, FileMetadata file) {
        Story story = new Story();
        story.valueOf();

        story.setCreateTime(LocalDateTime.now());
        story.setName(file.getName());
        story.setUrl(file.getUrl());
        story.setTakeTime(file.getToken());
        story.setOwnerId(user.getUserId());
        story.setSubject(StringUtils.EMPTY);

        String description = String.format("from: %s %s", file.getMake(), file.getModel());
        story.setDescription(description);

        Coordinate coordinate = new Coordinate(file.getLatitude(), file.getLongitude());
        story.setLocation(coordinate);
        return story;
    }
}
