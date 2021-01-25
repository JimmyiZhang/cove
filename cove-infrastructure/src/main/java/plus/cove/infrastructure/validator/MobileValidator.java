package plus.cove.infrastructure.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 手机号校验器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^(\\+?0?86\\-?)?1\\d{10}$");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(s) || MOBILE_PATTERN.matcher(s).matches();
    }

    @Override
    public void initialize(Mobile constraintAnnotation) {

    }
}