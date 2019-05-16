package com.carbybus.cove.domain.entity.coordinate;

import lombok.Data;

/**
 * 坐标接口
 * 可用于计算距离，转换
 */
@Data
public class Coordinate {
    /**
     * 经度
     */
    private double latitude;

    /**
     * 纬度
     */
    private double longitude;
}