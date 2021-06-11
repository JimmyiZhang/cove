package plus.cove.jazzy.domain.entity.district;

import lombok.Data;
import plus.cove.jazzy.domain.entity.coordinate.Coordinate;

/**
 * 城市
 * 包括城市名称，城市中心坐标
 *
 * @author jimmy.zhang
 * @date 2019-07-24
 */
@Data
public class DistrictCity  {
    /**
     * 区域代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 坐标
     */
    private Coordinate location;
}
