package plus.cove.jazzy.domain.entity.city;

import com.baomidou.mybatisplus.annotation.TableName;
import plus.cove.jazzy.domain.entity.coordinate.Coordinate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultEntity;

/**
 * 城市
 * 包括城市名称，城市中心坐标
 *
 * @author jimmy.zhang
 * @date 2019-07-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "city")
public class City extends DefaultEntity {
    private String name;
    private String title;
    private Coordinate location;
    private CityType type;
}
