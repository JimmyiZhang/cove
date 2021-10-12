package plus.cove.jazzy.repository;


import org.apache.ibatis.annotations.Mapper;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.global.District;
import plus.cove.jazzy.domain.entity.global.DistrictCity;
import plus.cove.jazzy.domain.entity.global.DistrictType;

import java.util.List;

/**
 * 城市仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Mapper
public interface GlobalDistrictRepository extends MybatisRepository<District, Long> {
    /**
     * 获取过程和城市
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-25
     */
    List<DistrictCity> selectByType(DistrictType type);
}
