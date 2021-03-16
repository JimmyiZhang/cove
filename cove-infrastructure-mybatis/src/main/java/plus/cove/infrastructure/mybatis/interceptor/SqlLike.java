package plus.cove.infrastructure.mybatis.interceptor;

import java.lang.annotation.*;

/**
 * sql like处理
 * 转义sql like通配符%_[
 * 默认前后增加%%
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
    String prefix() default "%";

    /**
     * 后缀
     *
     * @return
     */
    String suffix() default "%";
}
