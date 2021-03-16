package plus.cove.jazzy.repository;


import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.common.District;
import plus.cove.jazzy.domain.entity.common.DistrictCity;
import plus.cove.jazzy.domain.entity.common.DistrictType;

import java.util.List;

/**
 * 城市仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface DistrictRepository extends MybatisRepository<District> {
    /** 
    * 获取过程和城市
    * @param  
    * @return  
    * @author jimmy.zhang 
    * @date 2019-07-25 
    */ 
    List<DistrictCity> selectByType(DistrictType type);
}
