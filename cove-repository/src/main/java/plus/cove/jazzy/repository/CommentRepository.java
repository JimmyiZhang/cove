package plus.cove.jazzy.repository;

import org.springframework.stereotype.Repository;
import plus.cove.jazzy.domain.entity.comment.Comment;
import tk.mybatis.mapper.common.Mapper;

/**
 * 消息仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface CommentRepository extends Mapper<Comment> {

}
