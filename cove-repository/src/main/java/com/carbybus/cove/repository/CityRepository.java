package com.carbybus.cove.repository;


import com.carbybus.cove.domain.entity.city.City;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 城市仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface CityRepository extends BaseRepository<City> {
}
