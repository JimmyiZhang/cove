package plus.cove.jazzy.domain.facility;

import plus.cove.infrastructure.component.BaseEnum;

/**
 * 限流维度
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public enum LimitingDimension implements BaseEnum {
    MINUTE(1, "分钟"),
    HOUR(2, "小时"),
    DAY(3, "天"),
    WEEK(4, "周");

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
    LimitingDimension(final int value, final String desc) {
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
