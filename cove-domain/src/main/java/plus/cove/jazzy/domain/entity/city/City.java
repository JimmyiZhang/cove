package plus.cove.jazzy.domain.entity.city;

import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultEntity;
import plus.cove.jazzy.domain.entity.coordinate.Coordinate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 城市
 * 包括城市名称，城市中心坐标
 *
 * @author jimmy.zhang
 * @date 2019-07-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "city")
public class City extends DefaultEntity {
    private String name;
    private String title;
    private CityType type;
    private Coordinate location;
}
