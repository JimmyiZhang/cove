package com.carbybus.cove.domain.entity.trailer;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.coordinate.Coordinate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 车辆里程
 * 总里程，行驶里程，运输里程，空位里程
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "trailer_mileage")
public class TrailerMileage {

    /**
     * 坐标
     */
    private Coordinate coordinate;

    /**
     * 行驶里程
     * 单位：米
     */
    private int transportMileage;

    /**
     * 重载里程
     * 单位：米
     */
    private int heavyMileage;

    /**
     * 空驶里程
     * 单元：米
     */
    private int vacantMileage;

    /**
     * 运输里程
     * 单元：米
     */
    private int carriageMileage;

    /**
     * 发生时间
     */
    private LocalDateTime startTime;
}