package com.carbybus.cove.domain.entity.company;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.carbybus.infrastructure.component.BaseEnum;

/**
 * 员工类别
 *
 * @author jimmy.zhang
 * @date 2019-04-29
 */
public enum EmployeeCategory implements BaseEnum {
    Leader(1, "管理者"),
    Captain(2, "车队长");

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
    EmployeeCategory(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}