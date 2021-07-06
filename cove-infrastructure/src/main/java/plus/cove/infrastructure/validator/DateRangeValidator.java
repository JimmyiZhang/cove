package plus.cove.infrastructure.validator;

import lombok.extern.slf4j.Slf4j;
import plus.cove.infrastructure.utils.DateTimeHelper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 时间校验器
 *
 * @author jimmy.zhang
 * @since 2.0
 */
@Slf4j
public class DateRangeValidator implements ConstraintValidator<DateRange, Object> {
    private DateRange range;

    @Override
    public void initialize(DateRange range) {
        this.range = range;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        // 转换日期
        LocalDate dt = tryParseValue(value, this.range.pattern());
        if (dt == null) {
            return false;
        }

        // 验证日期
        ChronoUnit unit = range.unit();
        int min = range.min();
        int max = range.max();

        LocalDate now = LocalDate.now();
        if (min > Integer.MIN_VALUE && dt.isBefore(now.plus(min, unit))) {
            return false;
        }
        if (max < Integer.MAX_VALUE && dt.isAfter(now.plus(max, unit))) {
            return false;
        }

        return true;
    }

    private LocalDate tryParseValue(Object value, String[] pattern) {
        // 日期模式
        if (value instanceof LocalDate) {
            return (LocalDate) value;
        }

        // 字符串模式
        if (value instanceof String) {
            // 是否匹配
            String actual = (String) value;
            return DateTimeHelper.tryParse(actual, pattern);
        }

        return null;
    }
}