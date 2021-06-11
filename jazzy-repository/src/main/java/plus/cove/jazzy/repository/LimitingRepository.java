package plus.cove.jazzy.repository;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.facility.Limiting;
import plus.cove.jazzy.domain.facility.LimitingCondition;
import plus.cove.jazzy.domain.facility.LimitingTarget;

/**
 * 限流仓储
 *
 * @author jimmy.zhang
 * @since 1.1
 */
@Repository
public interface LimitingRepository extends MybatisRepository<Limiting> {
    Integer selectTarget(@Param("cd") LimitingCondition condition);

    void saveTarget(@Param("tg") LimitingTarget target);
}
