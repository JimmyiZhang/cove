package plus.cove.jazzy.application.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.cove.infrastructure.caching.CacheUtils;
import plus.cove.jazzy.application.DistrictApplication;
import plus.cove.jazzy.domain.entity.common.DistrictCity;
import plus.cove.jazzy.domain.entity.common.DistrictType;
import plus.cove.jazzy.repository.DistrictRepository;

import java.util.List;


/**
 * 城市应用
 * <p>
 * 中华人民共和国民政部-行政区划代码
 * http://www.mca.gov.cn/article/sj/xzqh/2020/
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Slf4j
@Service
public class DistrictApplicationImpl implements DistrictApplication {
    private static final String CACHE_CITY = "CITY";

    @Autowired
    CacheUtils cacheUtils;
    @Autowired
    DistrictRepository districtRep;

    @Override
    public List<DistrictCity> findAllCity() {
        List<DistrictCity> cities = cacheUtils.get(CACHE_CITY, "ALL", List.class);
        if (cities == null) {
            cities = districtRep.selectByType(DistrictType.CITY);
            cacheUtils.put(CACHE_CITY, "ALL", cities);
        }
        return cities;
    }
}
