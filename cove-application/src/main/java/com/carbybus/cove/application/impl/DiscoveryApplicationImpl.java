package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.DiscoveryApplication;
import com.carbybus.cove.application.utils.CacheConstants;
import com.carbybus.cove.domain.entity.city.City;
import com.carbybus.cove.domain.entity.city.CityType;
import com.carbybus.cove.domain.entity.discovery.DiscoveryPlace;
import com.carbybus.cove.domain.view.DiscoveryInput;
import com.carbybus.cove.repository.CityRepository;
import com.carbybus.infrastructure.caching.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地图应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
@Slf4j
public class DiscoveryApplicationImpl implements DiscoveryApplication {
    @Autowired
    private CityRepository cityRep;
    @Autowired
    private CacheUtils cacheUtils;

    // 获取指定类型的城市
    private List<City> listCity(CityType type) {
        Object all = cacheUtils.get(CacheConstants.DISCOVERY_CITY, type.toString());
        if (all != null) {
            return (List<City>) all;
        }

        List<City> cites = cityRep.selectByType(type);
        cacheUtils.put(CacheConstants.DISCOVERY_CITY, type.toString(), cites);
        return cites;
    }

    @Override
    public DiscoveryPlace generatePlace(DiscoveryInput input) {

        switch (input.getLevel()) {
            // 全国城市
            case HARD:
                List<City> countries = this.listCity(CityType.COUNTRY);
                City country = countries.get(0);

                input.setLatitude(country.getLocation().getLatitude());
                input.setLongitude(country.getLocation().getLongitude());
                break;
            case EXPERT:
                List<City> cities = this.listCity(CityType.CITY);
                int index = RandomUtils.nextInt(0, cities.size());
                City city = cities.get(index);

                input.setLatitude(city.getLocation().getLatitude());
                input.setLongitude(city.getLocation().getLongitude());
                break;
            default:
                break;
        }

        DiscoveryPlace place = DiscoveryPlace.create(input.getLatitude(), input.getLongitude(), input.getLevel());
        return place;
    }
}
