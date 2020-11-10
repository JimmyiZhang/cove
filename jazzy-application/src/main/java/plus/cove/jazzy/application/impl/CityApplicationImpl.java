package plus.cove.jazzy.application.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.cove.infrastructure.caching.CacheUtils;
import plus.cove.jazzy.application.CityApplication;
import plus.cove.jazzy.domain.entity.city.City;
import plus.cove.jazzy.domain.entity.city.CityType;
import plus.cove.jazzy.repository.CityRepository;

import java.util.List;


/**
 * 城市应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Slf4j
@Service
public class CityApplicationImpl implements CityApplication {
    private static final String CACHE_CITY = "CITY";

    @Autowired
    CacheUtils cacheUtils;

    @Autowired
    CityRepository cityRep;

    @Override
    public List<City> findAllCity() {
        List<City> cities = cacheUtils.get(CACHE_CITY, "ALL", List.class);
        if (cities == null) {
            cities = cityRep.selectByType(CityType.CITY);
            cacheUtils.put(CACHE_CITY, "ALL", cities);
        }
        return cities;
    }
}
