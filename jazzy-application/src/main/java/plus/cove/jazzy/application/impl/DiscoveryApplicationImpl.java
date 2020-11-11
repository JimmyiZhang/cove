package plus.cove.jazzy.application.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.cove.infrastructure.http.HttpUtils;
import plus.cove.infrastructure.json.JsonUtils;
import plus.cove.jazzy.application.CityApplication;
import plus.cove.jazzy.application.DiscoveryApplication;
import plus.cove.jazzy.domain.entity.city.City;
import plus.cove.jazzy.domain.entity.discovery.DiscoveryPlace;
import plus.cove.jazzy.domain.entity.discovery.DiscoveryWallpaper;
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
    private static final String WALLPAPER_IMAGE = "https://api.muxiaoguo.cn/api/sjbz?apiKey=639db450e032fbb9ba762bd04bcb0a26&method=pc&type=sina";
    private static final String WALLPAPER_TITLE = "https://api.muxiaoguo.cn/api/dujitang?apiKey=4ce4843d828e7b1fc4d8a15e290419b4";
    @Autowired
    CityApplication cityApp;
    @Autowired
    HttpUtils httpUtils;
    @Autowired
    JsonUtils jsonUtils;

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

    @Override
    public DiscoveryWallpaper fetchWallpaper() {
        String imgJson = httpUtils.getJson(WALLPAPER_IMAGE);
        String image = jsonUtils.fromJson(imgJson, "data.imgurl");

        String ttlJson = httpUtils.getJson(WALLPAPER_TITLE);
        String title = jsonUtils.fromJson(ttlJson, "data.comment");

        DiscoveryWallpaper output = new DiscoveryWallpaper();
        output.with(image, title);
        return output;
    }
}
