package plus.cove.infrastructure.mybatis.interceptor;

import java.lang.annotation.*;
import java.time.temporal.ChronoUnit;

/**
 * sql date处理
 * 针对LocalDate增加一天处理
 *
 * @author jimmy.zhang
 * @since 2.0
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlDate {
    /**
     * 增加
     *
     * @return
     */
    int increment() default 0;

    /**
     * 单位
     *
     * @return
     */
    ChronoUnit unit() default ChronoUnit.DAYS;
}
