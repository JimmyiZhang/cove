package plus.cove.infrastructure.utils;

/**
 * 整型工具类
 *
 * @author jimmy.zhang
 * @date 2019-04-04
 */
public class IntegerHelper {
    /**
     * 私有构造器
     */
    private IntegerHelper() {
    }

    /**
     * 判断是null或者zero
     *
     * @param origin 原值
     * @return
     * @author jimmy.zhang
     * @date 2019-08-20
     */
    public static boolean isEmpty(Integer origin) {
        return origin == null || origin.intValue() == 0;
    }

    /**
     * 判断不是null和不是zero
     *
     * @param origin 原值
     * @return
     * @author jimmy.zhang
     * @date 2019-08-20
     */
    public static boolean isNotEmpty(Integer origin) {
        return !isEmpty(origin);
    }

    /**
     * 判断是否相等
     * null返回false
     *
     * @param origin 原始值
     * @param value  比较值
     * @return
     * @author jimmy.zhang
     * @date 2019-04-25
     */
    public static boolean equals(Integer origin, Integer value) {
        if (origin == null || value == null) {
            return false;
        }

        return origin.intValue() == value.intValue();
    }

    /**
     * 判断是否大于某值
     * null返回false
     *
     * @param origin 原始值
     * @param value  比较值
     * @return 比较结果
     * @author jimmy.zhang
     * @date 2019-04-04
     */
    public static boolean greaterThan(Integer origin, Integer value) {
        if (origin == null || value == null) {
            return false;
        }

        return origin != null && origin.intValue() > value;
    }

    /**
     * 判断是否大于或等于某值
     * null返回false
     *
     * @param origin 原始值
     * @param value  比较值
     * @return 比较结果
     * @author jimmy.zhang
     * @date 2019-04-04
     */
    public static boolean graterAndEquals(Integer origin, Integer value) {
        if (origin == null || value == null) {
            return false;
        }

        return origin.intValue() >= value.intValue();
    }

    /**
     * 判断是否小于某值
     * null返回false
     *
     * @param origin 原始值
     * @param value  比较值
     * @return 比较结果
     * @author jimmy.zhang
     * @date 2019-04-04
     */
    public static boolean lessThan(Integer origin, Integer value) {
        if (origin == null || value == null) {
            return false;
        }

        return origin.intValue() < value.intValue();
    }

    /**
     * 判断是否小于或等于某值
     * null返回false
     *
     * @param origin 原始值
     * @param value  比较值
     * @return 比较结果
     * @author jimmy.zhang
     * @date 2019-04-04
     */
    public static boolean lessThanAndEquals(Integer origin, Integer value) {
        if (origin == null || value == null) {
            return false;
        }

        return origin.intValue() <= value.intValue();
    }
}
