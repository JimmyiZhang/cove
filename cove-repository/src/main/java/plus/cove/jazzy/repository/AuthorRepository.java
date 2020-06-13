package plus.cove.jazzy.repository;


import org.springframework.stereotype.Repository;
import plus.cove.jazzy.domain.entity.user.Author;
import tk.mybatis.mapper.common.Mapper;

/**
 * 旅行者仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface AuthorRepository extends Mapper<Author> {
}
