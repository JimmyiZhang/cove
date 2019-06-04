package com.carbybus.infrastructure.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author jimmy.zhang
 * @date 2019-06-04
 */
public class DateTimeUtils {
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
}
