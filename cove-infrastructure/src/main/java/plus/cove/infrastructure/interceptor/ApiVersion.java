package plus.cove.infrastructure.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口版本
 * <p>
 * controller使用方法
 * <p>
 * 使用path
 * ApiVersion(value = "v1", source = ApiVersionSource.PATH)
 * GetMapping(value = "city")
 * <p>
 * 使用header
 * ApiVersion(value = "v1", source = ApiVersionSource.HEADER)
 * GetMapping(value = "city", headers = "X-API-VERSION=v1")
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
     * 版本号来源，path或header
     *
     * @return
     */
    ApiVersionSource source() default ApiVersionSource.PATH;

    /**
     * 版本号来源的key
     * 适用于header和parameter
     *
     * @return
     */
    String sourceKey() default "Api-Version";
}
