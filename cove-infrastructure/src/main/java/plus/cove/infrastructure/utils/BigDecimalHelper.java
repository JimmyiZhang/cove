package plus.cove.infrastructure.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 小数工具类
 *
 * @author jimmy.zhang
 * @date 2019-04-04
 */
public class BigDecimalHelper {
    /**
     * 私有构造器
     */
    private BigDecimalHelper() {
    }

    /**
     * 相乘
     *
     * @param decimal 小数
     * @param integer 正数
     * @return
     * @author jimmy.zhang
     * @date 2019-08-20
     */
    public static int multiply(BigDecimal decimal, Integer integer) {
        return new BigDecimal(integer).multiply(decimal).setScale(0, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 相除
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static BigDecimal divide(Integer integer, BigDecimal decimal) {
        return new BigDecimal(integer).divide(decimal).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 四舍五入为double
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static double roundToDouble(Double origin, int scale) {
        return new BigDecimal(origin).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 四舍五入为BigDecimal
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static BigDecimal roundToDecimal(Double origin, int scale) {
        return new BigDecimal(origin).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 四舍五入
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static BigDecimal round(BigDecimal origin, int scale) {
        return origin.setScale(scale, RoundingMode.HALF_UP);
    }
}
