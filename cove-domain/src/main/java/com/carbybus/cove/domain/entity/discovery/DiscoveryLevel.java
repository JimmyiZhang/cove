package com.carbybus.cove.domain.entity.discovery;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.carbybus.infrastructure.component.BaseEnum;

/**
 * 探索难度
 * 分为：简单，一般，困难，专家
 * <p>
 * 难度包括：
 * 1. 起点的随机偏移量，经度坐标偏移，纬度采用修正值
 * 2. 探索区域的限制范围，经度坐标偏移，纬度采用修正值
 * 3. 默认显示级别
 * 4. 探索限制级别
 *
 * @author jimmy.zhang
 * @date 2019-07-24
 */
public enum DiscoveryLevel implements BaseEnum {
    // 起点坐标一定区域内
    EASY(1, 0.05, 0.01, 12, 17, "简单"),

    // 起点坐标一定区域内
    GENERAL(2, 0.1, 0.02, 10, 16, "一般"),

    // 全国所有城市
    HARD(3, 0.2, 0.02, 5, 16, "困难"),

    // 全国所有区域
    EXPERT(4, 1, 0.04, 5, 15, "专家");

    /**
     * 枚举值
     */
    @EnumValue
    private int value;

    /**
     * 修正系数
     */
    private double factor = 0.5;

    /**
     * 起点偏移（经度）
     */
    private double offset;

    /**
     * 探索限制（经度）
     */
    private double limit;

    /**
     * 查看级别
     */
    private int view;

    /**
     * 探索级别
     */
    private int find;

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
    DiscoveryLevel(
            final int value,
            final double offset,
            final double limit,
            final int view,
            final int find,
            final String desc) {
        this.value = value;
        this.offset = offset;
        this.limit = limit;
        this.view = view;
        this.find = find;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public double getFactor() {
        return factor;
    }

    public double getOffset() {
        return offset;
    }

    public double getLimit() {
        return limit;
    }

    public int getView() {
        return view;
    }

    public int getFind() {
        return find;
    }
}

