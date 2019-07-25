package com.carbybus.cove.repository;


import com.carbybus.cove.domain.entity.city.City;
import com.carbybus.cove.domain.entity.city.CityType;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 城市仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface CityRepository extends BaseRepository<City> {
    /** 
    * 获取过程和城市
    * @param  
    * @return  
    * @author jimmy.zhang 
    * @date 2019-07-25 
    */ 
    List<City> selectByType(CityType type);
}
