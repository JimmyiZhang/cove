package plus.cove.infrastructure.utils;

import cn.hutool.core.util.StrUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author jimmy.zhang
 * @date 2019-06-04
 */
public class DateTimeHelper {
    private DateTimeHelper() {
    }

    /**
     * 从时间装换为本地时间
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-04
     */
    public static LocalDateTime fromDate(Date date) {
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(date.toInstant(), zone);
    }

    /**
     * 从本地时间转化为时间
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-31
     */
    public static Date toDate(LocalDateTime local) {
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(local.atZone(zone).toInstant());
    }

    /**
     * 尝试转化
     * <p>
     * 空字符串返回null
     * 转化失败返回null
     *
     * @param value    转换的值
     * @param patterns 转换模式，比如yyyy-MM-dd
     * @return
     */
    public static LocalDate tryParse(String value, String[] patterns) {
        // 是否为空
        if (value == null || value.isEmpty()) {
            return null;
        }

        // 是否匹配
        LocalDate actual = null;
        for (String pattern : patterns) {
            // 匹配
            String delimiter = pattern.replaceAll("[\\s|Y|y|M|m|d]", "");
            if (!delimiter.isEmpty()) {
                if (!StrUtil.containsAny(value, delimiter)) {
                    continue;
                }
            }

            // 强转，测试
            try {
                actual = LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
            } catch (Exception e) {
                actual = null;
            }

            // 正常，返回
            if (actual != null) {
                break;
            }
        }
        return actual;
    }
}
