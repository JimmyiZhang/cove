package com.carbybus.cove.domain.entity.trailer;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 行驶状态
 */
public enum TrailerRunState implements IEnum<Integer> {
    Transiting(1, "在途"),
    Idle(2, "闲置"),
    Unknown(9, "未知");

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
    TrailerRunState(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}