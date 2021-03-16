package plus.cove.jazzy.application;


import plus.cove.jazzy.domain.entity.common.DistrictCity;

import java.util.List;

/**
 * 城市应用
 *
 * @author jimmy.zhang
 * @date 2020-11-01
 */
public interface DistrictApplication {
    /**
     * 获取
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    List<DistrictCity> findAllCity();
}
