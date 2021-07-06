package plus.cove.infrastructure.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.temporal.ChronoUnit;


/**
 * 时间验证
 *
 * @author jimmy.zhang
 * @since 2.0
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
@Documented
public @interface DateRange {
    String message() default "无效的日期";

    /**
     * 最小值
     *
     * @return
     */
    int min() default Integer.MIN_VALUE;

    /**
     * 最大值
     *
     * @return
     */
    int max() default Integer.MAX_VALUE;

    /**
     * 时间单位
     *
     * @return
     */
    ChronoUnit unit() default ChronoUnit.DAYS;

    /**
     * 模式
     * 用于转换
     *
     * @return
     */
    String[] pattern() default "yyyy-MM-dd";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
