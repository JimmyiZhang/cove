package com.carbybus.cove.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 消息
 * 包括预警消息
 * 一条预警，发给给相应的车队长和管理者
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "message")
public class Message extends DefaultEntity {
    /**
     * 接受者
     */
    private long receiverId;

    /**
     * 时间来源
     * 包括事件id
     */
    private long resourceId;

    /**
     * 是否已读
     */
    @TableField("is_read")
    private boolean readStatus;

    /**
     * 已读时间
     */
    private LocalDateTime readTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    public static Message create(long receiverId, long resourceId) {
        LocalDateTime epoch = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());

        Message message = new Message();
        message.valueOf();
        message.setReceiverId(receiverId)
                .setResourceId(resourceId)
                .setCreateTime(LocalDateTime.now())
                .setReadTime(LocalDateTime.now());

        return message;
    }
}