package plus.cove.jazzy.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.district.District;
import plus.cove.jazzy.domain.entity.district.DistrictCity;
import plus.cove.jazzy.domain.entity.district.DistrictType;

import java.util.List;

/**
 * 城市仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Mapper
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
