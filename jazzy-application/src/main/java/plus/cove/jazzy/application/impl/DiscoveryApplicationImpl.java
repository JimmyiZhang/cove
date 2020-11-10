package plus.cove.jazzy.application.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.cove.jazzy.application.CityApplication;
import plus.cove.jazzy.application.DiscoveryApplication;
import plus.cove.jazzy.domain.entity.city.City;
import plus.cove.jazzy.domain.entity.discovery.DiscoveryPlace;
import plus.cove.jazzy.domain.view.DiscoveryInput;

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
    CityApplication cityApp;

    @Override
    public DiscoveryPlace generatePlace(DiscoveryInput input) {
        switch (input.getLevel()) {
            // 全国城市
            case HARD:
            case EXPERT:
                List<City> cities = cityApp.findAllCity();
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
