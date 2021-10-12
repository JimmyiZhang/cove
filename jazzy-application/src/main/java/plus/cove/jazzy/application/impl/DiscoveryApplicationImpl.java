package plus.cove.jazzy.application.impl;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.cove.jazzy.application.DiscoveryApplication;
import plus.cove.jazzy.application.DistrictApplication;
import plus.cove.jazzy.domain.entity.discovery.DiscoveryPlace;
import plus.cove.jazzy.domain.entity.global.DistrictCity;
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
    DistrictApplication cityApp;

    @Override
    public DiscoveryPlace generatePlace(DiscoveryInput input) {
        switch (input.getLevel()) {
            // 全国城市
            case HARD:
            case EXPERT:
                List<DistrictCity> cities = cityApp.findAllCity();
                int index = RandomUtil.randomInt(0, cities.size());
                DistrictCity district = cities.get(index);

                input.setLatitude(district.getLocation().getLatitude());
                input.setLongitude(district.getLocation().getLongitude());
                break;
            default:
                break;
        }

        DiscoveryPlace place = DiscoveryPlace.create(input.getLatitude(), input.getLongitude(), input.getLevel());
        return place;
    }
}
