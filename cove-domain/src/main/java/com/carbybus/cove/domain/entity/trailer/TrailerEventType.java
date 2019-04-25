package com.carbybus.cove.domain.entity.trailer;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 车辆报警类型
 */
public enum TrailerEventType implements IEnum<Integer> {
    SpeedWarning(1, "超速预警"),
    FuelWarning(2, "油量预警"),
    AccidentWarning(3, "事故预警"),
    DeviceWarning(4, "设备预警"),
    StoppingEvent(5, "超长停车"),
    FuelingEvent(6, "加油"),
    LoadingEvent(7, "装车"),
    UnloadingEvent(8, "交车");

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