package plus.cove.infrastructure.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口幂等性注解
 * 使用：在接口的方法上使用此方法
 * 前提：前端需要request的query中增加参数且保证不重复使用
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {
}
