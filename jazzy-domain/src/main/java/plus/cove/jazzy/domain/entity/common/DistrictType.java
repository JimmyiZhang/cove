package plus.cove.jazzy.domain.entity.common;

import plus.cove.infrastructure.component.BaseEnum;

/**
 * 城市类别
 * 分为：省 市 区
 *
 * @author jimmy.zhang
 * @date 2019-07-24
 */
public enum DistrictType implements BaseEnum {
    COUNTRY(0, "国"),
    PROVINCE(1, "省"),
    CITY(2, "市"),
    DISTRICT(3, "区");

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
    DistrictType(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

