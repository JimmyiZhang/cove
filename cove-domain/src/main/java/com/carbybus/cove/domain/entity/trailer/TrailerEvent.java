package com.carbybus.cove.domain.entity.trailer;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.coordinate.Coordinate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 车辆时间
 * 包括预警和常规事件
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "trailer_event")
public class TrailerEvent extends TrailerEntity {
    /**
     * 坐标
     */
    private Coordinate coordinate;

    /**
     * 位置
     */
    private String location;

    /**
     * 内容
     */
    private String content;

    /**
     * 发生时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 持续时间
     */
    private int duration;

}