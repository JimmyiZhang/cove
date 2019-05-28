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
    private Double minLatitude;
    private Double minLongitude;

    private Double maxLatitude;
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