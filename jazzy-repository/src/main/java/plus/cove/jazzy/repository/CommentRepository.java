package plus.cove.jazzy.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.comment.Comment;

import java.util.List;

/**
 * 消息仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Mapper
@Repository
public interface CommentRepository extends MybatisRepository<Comment> {
    List<Comment> selectMany();
}
