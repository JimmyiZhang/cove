package plus.cove.jazzy.repository;

import plus.cove.jazzy.domain.entity.comment.Comment;
import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.component.BaseRepository;

/**
 * 消息仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface CommentRepository extends BaseRepository<Comment> {

}
