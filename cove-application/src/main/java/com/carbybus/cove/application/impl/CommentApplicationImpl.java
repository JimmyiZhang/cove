package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.CommentApplication;
import com.carbybus.cove.domain.entity.comment.Comment;
import com.carbybus.cove.domain.view.CommentCreateInput;
import com.carbybus.cove.repository.CommentRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class CommentApplicationImpl extends DefaultApplication<CommentRepository, Comment> implements CommentApplication {
    @Autowired
    private CommentRepository messageRep;

    @Override
    public ActionResult create(CommentCreateInput input) {
        ActionResult result = new ActionResult();

        Comment comment = input.Convert();

        // 保存数据
        this.repository.insert(comment);
        result.succeed();
        return result;
    }

}
