package com.carbybus.infrastructure.validator;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.exception.ValidatorError;

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
        ActionResult<C> result = new ActionResult<>(0, "ok");
        if (input == null) {
            result.fail(ValidatorError.METHOD_ARGUMENT);
            return result;
        }

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<T>> validates = validator.validate(input, groups);

        if (validates != null && !validates.isEmpty()) {
            ConstraintViolation<T> validate = validates.stream()
                    .findFirst().orElseThrow(IllegalArgumentException::new);

            result.fail(ValidatorError.METHOD_ARGUMENT.getCode(),
                    validate.getMessage());
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
        ActionResult<C> result = new ActionResult<>(0, "ok");

        if (inputs == null || inputs.isEmpty()) {
            result.fail(ValidatorError.METHOD_ARGUMENT);
            return result;
        }

        for (E input : inputs) {
            ActionResult<C> validated = valid(input, groups);
            if (validated.noSuccess()) {
                result.fail(validated.getCode(), validated.getMessage());
                break;
            }
        }

        return result;
    }
}
