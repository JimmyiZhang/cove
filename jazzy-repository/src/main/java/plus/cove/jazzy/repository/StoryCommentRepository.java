package plus.cove.jazzy.repository;

import org.apache.ibatis.annotations.Mapper;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.story.StoryComment;

import java.util.List;

/**
 * 消息仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Mapper
public interface StoryCommentRepository extends MybatisRepository<StoryComment, Long> {
    List<StoryComment> selectMany();
}
