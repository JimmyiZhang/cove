package plus.cove.infrastructure.coordinate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 坐标接口
 * 可用于计算距离，转换
 *
 * @author Jimmy.Zhang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultCoordinate implements BaseCoordinate {
    private Double latitude;
    private Double longitude;
}