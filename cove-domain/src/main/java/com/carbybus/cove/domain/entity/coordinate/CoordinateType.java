package com.carbybus.cove.domain.entity.coordinate;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 坐标类型
 * 支持wcg84和gcj02
 */
public enum CoordinateType implements IEnum<Integer> {
    WGS84(1, "国际标准"),
    GCJ02(2, "中国标准");

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
    CoordinateType(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}