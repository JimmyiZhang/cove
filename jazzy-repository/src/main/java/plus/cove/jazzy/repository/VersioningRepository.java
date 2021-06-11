package plus.cove.jazzy.repository;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.facility.Versioning;
import plus.cove.jazzy.domain.facility.VersioningCondition;

/**
 * 版本仓储
 *
 * @author jimmy.zhang
 * @since 1.1
 */
@Repository
public interface VersioningRepository extends MybatisRepository<Versioning> {
    Versioning selectEntity(@Param("cd") VersioningCondition condition);

    void insertEntity(@Param("et") Versioning entity);

    void updateEntity(@Param("et") Versioning entity);
}
