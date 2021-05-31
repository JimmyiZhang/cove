package plus.cove.infrastructure.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口版本
 *
 * @author jimmy.zhang
 * @since 2.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {
    /**
     * 版本号，v1
     *
     * @return
     */
    String[] value();

    /**
     * 版本号来源，uri或header
     *
     * @return
     */
    ApiVersionSource source();

    /**
     * 版本号来源的key
     * 适用于header和parameter
     *
     * @return
     */
    String sourceKey() default "X-API-VERSION";
}
