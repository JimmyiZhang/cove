package plus.cove.jazzy.domain.entity.user;

import com.baomidou.mybatisplus.annotation.EnumValue;
import plus.cove.infrastructure.component.BaseEnum;

/**
 * 性别类别
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @date 2019-05-24
 */
public enum UserGender implements BaseEnum {
    NONE(1, "未知"),
    MALE(2, "男"),
    FEMALE(3, "女");

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
    UserGender(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
