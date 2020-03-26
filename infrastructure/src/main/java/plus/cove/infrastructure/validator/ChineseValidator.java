package plus.cove.infrastructure.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 中文校验器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class ChineseValidator implements ConstraintValidator<Chinese, String> {
    private static final Pattern CHINESE_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5]+$");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(s) || CHINESE_PATTERN.matcher(s).matches();
    }

    @Override
    public void initialize(Chinese constraintAnnotation) {

    }
}
