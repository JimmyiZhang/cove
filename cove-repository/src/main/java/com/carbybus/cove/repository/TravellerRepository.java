package com.carbybus.cove.repository;


import com.carbybus.cove.domain.entity.user.Traveller;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 旅行者仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface TravellerRepository extends BaseRepository<Traveller> {
}
