package com.carbybus.cove.domain.entity.trailer;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 车辆油量类型
 */
public enum TrailerFuelType implements IEnum<Integer> {
    Increase(1, "加油"),
    Decrease(2,"用油");

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
    TrailerFuelType(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}