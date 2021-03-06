package plus.cove.jazzy.domain.entity.coordinate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 坐标接口
 * 可用于计算距离，转换
 *
 * @author Jimmy.Zhang
 */
@Data
public class Coordinate {
    @JsonIgnore
    public final static Coordinate EMPTY = new Coordinate(-1D, -1D);

    private Double latitude;
    private Double longitude;

    public Coordinate() {
    }

    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * 是否为空
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-24
     */
    @JsonIgnore
    public boolean isEmpty() {
        return this.latitude < 0 && this.longitude < 0;
    }
}