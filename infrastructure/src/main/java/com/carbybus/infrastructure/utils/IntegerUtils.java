package com.carbybus.infrastructure.utils;

/**
 * 整型工具类
 *
 * @author jimmy.zhang
 * @date 2019-04-04
 */
public class IntegerUtils {
    /**
     * 工具类使用私有构造器覆盖公共构造器，防止公共构造器被调用
     * Sonar Code smell Major squid:S1118
     */
    private IntegerUtils() {
    }

    /**
     * 判断是否相等
     * null返回false
     *
     * @param origin Integer值
     * @return
     * @author jimmy.zhang
     * @date 2019-04-25
     */
    public static boolean equals(Integer origin, int value) {
        return origin != null && origin.intValue() == value;
    }

    /**
     * 判断是否大于某值
     * null返回false
     *
     * @param origin Integer值
     * @param value  比较值
     * @return 比较结果
     * @author jimmy.zhang
     * @date 2019-04-04
     */
    public static boolean greaterThan(Integer origin, int value) {
        return origin != null && origin.intValue() > value;
    }

    /**
     * 判断是否小于某值
     *
     * @param origin Integer值
     * @param value  比较值
     * @return 比较结果
     * @author jimmy.zhang
     * @date 2019-04-04
     */
    public static boolean lessThan(Integer origin, int value) {
        return origin != null && origin.intValue() < value;
    }
}
