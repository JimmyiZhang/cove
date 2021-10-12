package plus.cove.jazzy.domain.entity.global;

import lombok.Data;
import plus.cove.jazzy.domain.entity.coordinate.Coordinate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 城市
 * 包括城市名称，城市中心坐标
 *
 * @author jimmy.zhang
 * @date 2019-07-24
 */
@Data
@Entity
@Table(name = "global_district")
public class District {
    /**
     * 编码
     */
    @Id
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 全称
     */
    private String title;

    /**
     * 类型
     */
    private DistrictType type;

    /**
     * 坐标
     */
    private Coordinate location;
}
