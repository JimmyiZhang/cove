package com.carbybus.cove.application;

import com.carbybus.cove.domain.entity.comment.Comment;
import com.carbybus.cove.domain.view.CommentCreateInput;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.BaseApplication;

/**
 * 消息应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface CommentApplication extends BaseApplication<Comment> {
    /**
     * 创建
     *
     * @param comment 实体
     * @return 创建结果
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    ActionResult create(CommentCreateInput comment);
}
