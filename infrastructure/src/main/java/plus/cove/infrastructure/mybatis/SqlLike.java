package plus.cove.infrastructure.mybatis;

import java.lang.annotation.*;

/**
 * sql like通配符转义
 * 包括%_[
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlLike {

    /**
     * 前缀
     *
     * @return
     */
    String prefix() default "";

    /**
     * 后缀
     *
     * @return
     */
    String suffix() default "";
}
