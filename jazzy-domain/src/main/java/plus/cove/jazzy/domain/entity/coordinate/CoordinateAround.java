package plus.cove.jazzy.domain.entity.coordinate;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 坐标四周
 * 可用于计算距离，转换
 *
 * @author Jimmy.Zhang
 */
@Data
@Accessors(chain = true)
public class CoordinateAround {
    @NotNull(message="最小纬度不能为空")
    private Double minLatitude;
    @NotNull(message="最小经度不能为空")
    private Double minLongitude;

    @NotNull(message="最大纬度不能为空")
    private Double maxLatitude;
    @NotNull(message="最大经度不能为空")
    private Double maxLongitude;


    public CoordinateAround() {
    }

    public CoordinateAround(Double minLatitude,
                            Double minLongitude,
                            Double maxLatitude,
                            Double maxLongitude) {
        this.minLatitude = minLatitude;
        this.minLongitude = minLongitude;

        this.maxLatitude = maxLatitude;
        this.maxLongitude = maxLongitude;
    }
}