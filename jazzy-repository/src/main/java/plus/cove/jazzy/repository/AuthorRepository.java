package plus.cove.jazzy.repository;


import com.github.pagehelper.PageRowBounds;
import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.user.Author;
import plus.cove.jazzy.domain.view.AuthorListInput;
import plus.cove.jazzy.domain.view.AuthorListOutput;

import java.util.List;

/**
 * 旅行者仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface AuthorRepository extends MybatisRepository<Author> {
    List<AuthorListOutput> selectMany(AuthorListInput input, PageRowBounds page);
}
