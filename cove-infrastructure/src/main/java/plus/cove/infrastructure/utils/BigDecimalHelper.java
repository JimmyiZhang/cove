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
