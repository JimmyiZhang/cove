package plus.cove.jazzy.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.facility.Versioning;
import plus.cove.jazzy.domain.facility.VersioningCondition;

/**
 * 版本仓储
 *
 * @author jimmy.zhang
 * @since 1.1
 */
@Mapper
public interface FacilityVersioningRepository extends MybatisRepository<Versioning, Long> {
    Versioning selectVersioning(@Param("cd") VersioningCondition condition);

    void insertVersioning(@Param("et") Versioning entity);

    void updateVersioning(@Param("et") Versioning entity);
}
