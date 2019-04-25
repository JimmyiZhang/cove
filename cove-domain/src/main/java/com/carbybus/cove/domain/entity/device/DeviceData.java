package com.carbybus.cove.domain.entity.device;

import lombok.Data;

/**
 * 设备数据
 * 从设备转换的可用设备数据
 */
@Data
public class DeviceData {

    /**
     * 设备号
     */
    private String deviceNo;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 经度
     */
    private double longtitude;

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
     * 油量
     * 单位: 厘米
     */
    private int fuel;

    /**
     * 载重1
     * 单位： 千克
     */
    private int load1;

    /**
     * 载重2
     * 单位： 千克
     */
    private int load2;

    /**
     * 上报时间
     */
    private long uploadTime;

    /**
     * 创建时间
     */
    private long createTime;
}