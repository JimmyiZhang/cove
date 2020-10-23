package plus.cove.jazzy.application.impl;

import plus.cove.infrastructure.caching.CacheUtils;
import plus.cove.jazzy.application.DiscoveryApplication;
import plus.cove.jazzy.domain.entity.city.City;
import plus.cove.jazzy.domain.entity.city.CityType;
import plus.cove.jazzy.domain.entity.discovery.DiscoveryPlace;
import plus.cove.jazzy.domain.view.DiscoveryInput;
import plus.cove.jazzy.repository.CityRepository;
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
    private static final String DISCOVERY_CITY = "DISCOVERY_CITY";

    @Autowired
    private CityRepository cityRep;
    @Autowired
    private CacheUtils cacheUtils;

    /**
     * 获取指定类型的城市
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-24
     */
    private List<City> listCity(CityType type) {
        Object all = cacheUtils.get(DISCOVERY_CITY, type.toString());
        if (all != null) {
            return (List<City>) all;
        }

        List<City> cites = cityRep.selectByType(type);
        cacheUtils.put(DISCOVERY_CITY, type.toString(), cites);
        return cites;
    }

    @Override
    public DiscoveryPlace generatePlace(DiscoveryInput input) {
        switch (input.getLevel()) {
            // 全国城市
            case HARD:
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
