package com.carbybus.cove.domain.entity.trailer;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 车辆报警类型
 */
public enum TrailerEventType implements IEnum<Integer> {
    SPEED_WARNING(1, "超速预警"),
    FUEL_WARNING(2, "油量预警"),
    ACCIDENT_WARNING(3, "事故预警"),
    DEVICE_WARNING(4, "设备预警"),
    STOPPING_EVENT(5, "超长停车"),
    FUELING_EVENT(6, "加油"),
    LOADING_EVENT(7, "装车"),
    UNLOADING_EVENT(8, "交车");

    /**
     * 枚举值
     */
    private int value;

    /**
     * 枚举描述
     */
    private String desc;

    /**
     * 构造函数
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-04-18
     */
    TrailerEventType(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}