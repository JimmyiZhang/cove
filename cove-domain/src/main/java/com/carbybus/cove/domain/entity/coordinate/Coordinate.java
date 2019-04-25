package com.carbybus.cove.domain.entity.coordinate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    private double longtitude;
}