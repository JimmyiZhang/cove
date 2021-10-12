package plus.cove.jazzy.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.component.PageModel;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.author.Author;
import plus.cove.jazzy.domain.view.AuthorListInput;
import plus.cove.jazzy.domain.view.AuthorListOutput;

import java.util.List;

/**
 * 旅行者仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Mapper
@Repository
public interface AuthorRepository extends MybatisRepository<Author, Long> {
    List<AuthorListOutput> selectMany(AuthorListInput input, PageModel page);
}
