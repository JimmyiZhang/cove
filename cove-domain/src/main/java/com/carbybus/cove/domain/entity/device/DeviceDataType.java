package com.carbybus.cove.domain.entity.device;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 设备数据类型
 */
public enum DeviceDataType implements IEnum<Integer> {
    NORMAL(1, "正常"),
    VOLTAGE_LOW_ALERM(2, "欠压报警"),
    POWER_DOWN_ALERM(3, "掉电报警");

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
    DeviceDataType(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
