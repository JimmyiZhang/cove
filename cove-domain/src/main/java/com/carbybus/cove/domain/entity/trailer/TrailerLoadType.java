package com.carbybus.cove.domain.entity.trailer;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 载重类型
 */
public enum TrailerLoadType implements IEnum<Integer> {
    Heavy(1, "重驶"),
    Vacant(2, "空驶");

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
    TrailerLoadType(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}