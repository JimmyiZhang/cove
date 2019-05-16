package com.carbybus.cove.domain.entity.trailer;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.coordinate.Coordinate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 车辆油量
 * 加油信息
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "trailer_fuel")
public class TrailerFuel extends TrailerEntity {

    /**
     * 坐标
     */
    private Coordinate coordinate;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 开始油量
     */
    private int startFuel;

    /**
     * 结束油量
     */
    private int endFuel;

    /**
     * 差值
     */
    private int valueFuel;

    /**
     * 时长
     * 单位:分钟
     */
    private int duration;

}