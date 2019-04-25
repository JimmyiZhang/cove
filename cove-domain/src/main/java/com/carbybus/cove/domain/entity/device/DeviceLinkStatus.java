package com.carbybus.cove.domain.entity.device;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 设备连接状态
 */
public enum DeviceLinkStatus implements IEnum<Integer> {
    Online(1, "在线"),
    Offline(2, "离线");

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
    DeviceLinkStatus(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}