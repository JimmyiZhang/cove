package com.carbybus.cove.domain.entity.coordinate;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 坐标接口
 * 可用于计算距离，转换
 *
 * @author Jimmy.Zhang
 */
@Data
@Accessors(chain = true)
public class Coordinate {
    private Double latitude;
    private Double longitude;

    public Coordinate() {
    }

    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}