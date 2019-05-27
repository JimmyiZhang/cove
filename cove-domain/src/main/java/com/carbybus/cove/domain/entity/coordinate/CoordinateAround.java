package com.carbybus.cove.domain.entity.coordinate;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 坐标四周
 * 可用于计算距离，转换
 *
 * @author Jimmy.Zhang
 */
@Data
@Accessors(chain = true)
public class CoordinateAround {
    private Coordinate southeast;
    private Coordinate northwest;

    public CoordinateAround() {
    }

    public CoordinateAround(Double southeastLatitude,
                            Double southeastLongitude,
                            Double northwestLatitude,
                            Double northwestLongitude) {
        this.southeast = new Coordinate(southeastLatitude, southeastLongitude);
        this.northwest = new Coordinate(northwestLatitude, northwestLongitude);
    }
}