package plus.cove.jazzy.domain.entity.account;

import plus.cove.infrastructure.component.BaseEnum;

/**
 * 账号状态
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @date 2019-05-24
 */
public enum UserStatus implements BaseEnum {
    NONE(1, "未知的"),
    ACTIVE(2, "激活的"),
    DISABLED(3, "禁用的");

    /**
     * 枚举值
     */
    private final int value;

    /**
     * 枚举描述
     */
    private final String desc;

    /**
     * 构造函数
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-04-18
     */
    UserStatus(final int value, final String desc) {
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
