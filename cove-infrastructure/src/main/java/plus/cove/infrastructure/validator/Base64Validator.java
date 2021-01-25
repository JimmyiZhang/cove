package plus.cove.infrastructure.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * base64校验器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class Base64Validator implements ConstraintValidator<Base64, String> {
    private static final Pattern BASE64_PATTERN = Pattern.compile("^data:image/(?<img>\\w{3,4});base64,");
    private static final int BASE64_MIN_LENGTH = 24;

    private Base64 constraint;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(s)) {
            return true;
        }

        // 长度判断
        if (s.length() < BASE64_MIN_LENGTH) {
            return false;
        }

        // 格式判断
        String header = s.substring(0, BASE64_MIN_LENGTH);
        Matcher matcher = BASE64_PATTERN.matcher(header);
        boolean find = matcher.find();
        if (!find) {
            return false;
        }

        // 内容判断
        String format = constraint.format();
        if (format.length() == 0) {
            return false;
        }

        String group = matcher.group("img");
        return format.contains(group);
    }

    @Override
    public void initialize(Base64 constraintAnnotation) {
        this.constraint = constraintAnnotation;
    }
}
