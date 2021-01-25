package plus.cove.infrastructure.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * base64校验
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Base64Validator.class)
@Documented
public @interface Base64 {
    String message() default "无效的base64格式";

    String format() default "bmp,png,jpg,jpeg";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
