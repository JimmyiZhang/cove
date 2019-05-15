package com.carbybus.cove.api.config;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.exception.BusinessException;
import com.carbybus.infrastructure.exception.ServiceError;
import com.carbybus.infrastructure.exception.ValidatorError;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

/**
 * 统一异常
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ActionResult handleConstraintViolationException(ConstraintViolationException ex) {
        ConstraintViolation violation = ex.getConstraintViolations().stream()
                .findFirst()
                .orElse(null);

        String message;
        if (violation == null) {
            message = "未知的ConstraintViolation";
        } else {
            message = violation.getMessage();
        }

        ActionResult result = new ActionResult();
        result.fail(ValidatorError.CONSTRAINT_VIOLATION.getCode(), message);
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ActionResult handleMethodArgumentException(MethodArgumentNotValidException ex) {
        FieldError error = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .orElse(null);

        String message;
        if (error == null) {
            message = "未知的MethodArgument";
        } else {
            message = error.getDefaultMessage();
        }

        ActionResult result = new ActionResult();
        result.fail(ValidatorError.METHOD_ARGUMENT.getCode(), message);
        return result;
    }

    @ExceptionHandler(BusinessException.class)
    public ActionResult handleMethodArgumentException(BusinessException ex) {
        ActionResult result = new ActionResult();
        result.fail(ex.getCode(), ex.getMessage());
        return result;
    }

    // 权限异常
    @ExceptionHandler(AccessDeniedException.class)
    public ActionResult handleBadRequestException(Exception ex) {
        ActionResult result = new ActionResult();
        result.fail(ServiceError.BAD_REQUEST, ex.getMessage());
        return result;
    }

    // 其他异常
    @ExceptionHandler(Exception.class)
    public ActionResult handleServerErrorException(Exception ex) {
        ActionResult result = new ActionResult();
        result.fail(ServiceError.SERVER_ERROR, ex.getMessage());
        return result;
    }
}
