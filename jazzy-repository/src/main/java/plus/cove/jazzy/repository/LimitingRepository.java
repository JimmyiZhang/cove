package plus.cove.jazzy.repository;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.limiting.Limiting;
import plus.cove.jazzy.domain.entity.limiting.LimitingCondition;
import plus.cove.jazzy.domain.entity.limiting.LimitingTarget;

/**
 * 限流仓储
 *
 * @author jimmy.zhang
 * @date 2021-06-02
 */
@Repository
public interface LimitingRepository extends MybatisRepository<Limiting> {
    Integer selectTarget(@Param("cd") LimitingCondition condition);

    void saveTarget(@Param("tg") LimitingTarget target);
}
