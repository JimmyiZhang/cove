package plus.cove.infrastructure.converter;


import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 字符串和时间转换器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class LocalTimeConverter implements Converter<String, LocalTime> {
    public LocalTimeConverter(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * 日期格式
     */
    private String dateFormat;

    @Override
    public LocalTime convert(String source) {
        if (source.isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalTime.parse(source, formatter);
    }
}
