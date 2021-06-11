package plus.cove.jazzy.domain.facility;

import plus.cove.infrastructure.component.BaseEnum;

/**
 * 版本状态
 *
 * @author jimmy.zhang
 * @since 1.1
 */
public enum VersioningState implements BaseEnum {
    INVALID(0, "无效的"),
    VALID(1, "有效的");

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
    VersioningState(final int value, final String desc) {
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
