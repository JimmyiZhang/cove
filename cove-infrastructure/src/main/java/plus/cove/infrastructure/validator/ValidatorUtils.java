package plus.cove.infrastructure.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.exception.ValidatorError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collection;
import java.util.Set;

/**
 * 验证工具类
 *
 * @author jimmy.zhang
 * @date 2019-04-04
 */
@Slf4j
@Component
public class ValidatorUtils {
    /**
     * 工具类使用私有构造器覆盖公共构造器，防止公共构造器被调用
     * Sonar Code smell Major squid:S1118
     */
    private ValidatorUtils() {
    }

    /**
     * 验证数据
     *
     * @param input 需要验证的数据
     * @author jimmy.zhang
     * @date 2019-04-04
     */
    public static <T, C> ActionResult<C> valid(T input, Class<?>... groups) {
        ActionResult<C> result = ActionResult.success();
        if (input == null) {
            result.fail(ValidatorError.INVALID_ARGUMENT);
            return result;
        }

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<T>> validates = validator.validate(input, groups);

        if (validates != null && !validates.isEmpty()) {
            ConstraintViolation<T> validate = validates.stream()
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);

            result.fail(ValidatorError.INVALID_ARGUMENT)
                    .message(validate.getMessage());
            return result;
        }

        return result;
    }

    /**
     * 校验集合
     *
     * @param inputs 校验的集合
     * @author jimmy.zhang
     * @date 2019-04-04
     */
    public static <E, C> ActionResult<C> validCollection(Collection<E> inputs, Class<?>... groups) {
        ActionResult<C> result = ActionResult.success();

        if (inputs == null || inputs.isEmpty()) {
            result.fail(ValidatorError.INVALID_ARGUMENT);
            return result;
        }

        for (E input : inputs) {
            ActionResult<C> validated = valid(input, groups);
            if (validated.noSuccess()) {
                result = validated;
                break;
            }
        }

        return result;
    }
}
