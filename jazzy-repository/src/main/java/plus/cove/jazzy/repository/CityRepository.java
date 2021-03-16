package plus.cove.jazzy.repository;


import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.city.City;
import plus.cove.jazzy.domain.entity.city.CityType;

import java.util.List;

/**
 * 城市仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface CityRepository extends MybatisRepository<City> {
    /** 
    * 获取过程和城市
    * @param  
    * @return  
    * @author jimmy.zhang 
    * @date 2019-07-25 
    */ 
    List<City> selectByType(CityType type);
}
