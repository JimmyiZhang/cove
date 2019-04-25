package com.carbybus.cove.domain.entity.trailer;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.trailer.TrailerEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 车辆设备数据
 * 根据设备数据进行换算
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "trailer_data")
public class TrailerData extends TrailerEntity {
    /**
     * 速度
     * 单位： 米/小时
     */
    private int speed;

    /**
     * 高度
     * 单位：米
     */
    private int height;

    /**
     * 角度0-360
     * 单位：度
     */
    private int angle;

    /**
     * 
     */
    private int fuel;

    /**
     * 油量
     * 单位: 厘米
     */
    private int load;

    /**
     * 运载台数
     */
    private int carriage;

    /**
     * 城市
     */
    private String city;

    /**
     * 详细地址区县
     */
    private String location;

    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;

}