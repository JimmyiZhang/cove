package com.carbybus.infrastructure.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间工具类
 *
 * @author jimmy.zhang
 * @date 2019-06-04
 */
public class DateTimeUtils {
    private DateTimeUtils() {
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
    * @param  
    * @return  
    * @author jimmy.zhang 
    * @date 2019-07-31 
    */ 
    public static Date toDate(LocalDateTime local) {
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(local.atZone(zone).toInstant());
    }
}
