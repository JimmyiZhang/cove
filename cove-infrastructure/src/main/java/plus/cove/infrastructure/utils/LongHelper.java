package plus.cove.infrastructure.utils;

/**
 * 长整型工具类
 *
 * @author jimmy.zhang
 * @date 2019-04-04
 */
public class LongHelper {
    /**
     * 私有构造器
     */
    private LongHelper() {
    }

    /**
     * 判断是null或者zero
     *
     * @param origin 原值
     * @return
     * @author jimmy.zhang
     * @date 2019-08-20
     */
    public static boolean isEmpty(Long origin) {
        return origin == null || origin.longValue() == 0;
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
    public static boolean equals(Long origin, long value) {
        return origin != null && origin.longValue() == value;
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
    public static boolean greaterThan(Long origin, long value) {
        return origin != null && origin.longValue() > value;
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
    public static boolean graterAndEquals(Long origin, long value) {
        return origin != null && origin.longValue() >= value;
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
    public static boolean lessThan(Long origin, long value) {
        return origin != null && origin.longValue() < value;
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
    public static boolean lessThanAndEquals(Long origin, long value) {
        return origin != null && origin.longValue() <= value;
    }
}
