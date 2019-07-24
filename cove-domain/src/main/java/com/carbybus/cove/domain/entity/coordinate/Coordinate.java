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
    public boolean isEmpty() {
        return this.latitude < 0 && this.longitude < 0;
    }
}