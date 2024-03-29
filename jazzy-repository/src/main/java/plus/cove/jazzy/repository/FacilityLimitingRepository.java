package plus.cove.jazzy.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
@Mapper
public interface FacilityLimitingRepository extends MybatisRepository<Limiting, Long> {
    Integer selectLimiting(@Param("cd") LimitingCondition condition);

    void saveLimiting(@Param("tg") LimitingTarget target);
}
