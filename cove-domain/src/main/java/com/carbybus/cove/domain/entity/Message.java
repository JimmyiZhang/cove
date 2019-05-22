package com.carbybus.cove.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 消息，留言
 *
 * @author Jimmy.Zhang
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "message")
public class Message extends DefaultEntity {
    /**
     * 接受者
     */
    private long receiverId;

    /**
     * 发送者
     */
    private long senderId;

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
    public static Message create(long receiverId, long senderId) {
        LocalDateTime epoch = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());

        Message message = new Message();
        message.valueOf();
        message.setReceiverId(receiverId)
                .setSenderId(senderId)
                .setCreateTime(LocalDateTime.now())
                .setReadTime(epoch);

        return message;
    }
}