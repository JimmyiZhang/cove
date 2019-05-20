package com.carbybus.cove.domain.entity.coordinate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.carbybus.infrastructure.component.BaseEnum;

/**
 * 坐标类型
 * 仅支持wcg84和gcj02
 *
 * @author Jimmy.Zhang
 */
public enum CoordinateType implements BaseEnum {
    WGS84(1, "国际标准"),
    GCJ02(2, "中国标准");

    /**
     * 枚举值
     */
    @EnumValue
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