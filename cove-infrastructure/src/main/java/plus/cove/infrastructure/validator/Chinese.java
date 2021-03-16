package plus.cove.infrastructure.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 中文校验
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChineseValidator.class)
@Documented
public @interface Chinese {
    String message() default "非中文";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
