package plus.cove.infrastructure.utils;

import plus.cove.infrastructure.component.BaseEnum;

/**
 * 枚举基类工具类
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class BaseEnumUtils {
    public static <E extends Enum<?> & BaseEnum> E valueOf(Class<E> enumClass, int value) {
        if (!enumClass.isEnum()) {
            return null;
        }

        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }
}
