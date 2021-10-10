package plus.cove.jazzy.api.config;

import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.exception.BusinessException;
import plus.cove.infrastructure.exception.ServiceError;
import plus.cove.infrastructure.exception.ValidatorError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 统一异常
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    /**
     * 截获验证异常
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-26
     */
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

        ActionResult result = ActionResult.failure(ValidatorError.INVALID_CONSTRAINT)
                .message(message);
        log.warn("ConstraintViolationException:", ex);
        return result;
    }

    /**
     * 截获验证异常
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-26
     */
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

        ActionResult result = ActionResult
                .failure(ValidatorError.INVALID_ARGUMENT)
                .message(message);
        log.warn("MethodArgumentNotValidException:", ex);
        return result;
    }

    /**
     * 截获统一的应用异常
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-26
     */
    @ExceptionHandler(BusinessException.class)
    public ActionResult handleMethodArgumentException(BusinessException ex) {
        ActionResult result = ActionResult.result()
                .fail(ex.getCode(), ex.getMessage());
        log.warn("BusinessException:", ex);
        return result;
    }

    /**
     * 截获权限异常
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-26
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ActionResult handleBadRequestException(AccessDeniedException ex) {
        ActionResult result = ActionResult
                .failure(ServiceError.BAD_REQUEST)
                .message(ex.getMessage());
        log.warn("AccessDeniedException:", ex);
        return result;
    }

    /**
     * 截获MyBatis异常
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-26
     */
    @ExceptionHandler(PersistenceException.class)
    public ActionResult handlePersistenceException(PersistenceException ex) {
        ActionResult result = ActionResult.result();

        Throwable cause = ex.getCause();
        if (cause != null && cause instanceof BusinessException) {
            BusinessException e = (BusinessException) cause;
            result.fail(e.getCode(), e.getMessage());
        } else {
            result.fail(ServiceError.SERVER_ERROR, ex.getMessage());
        }

        log.error("MyBatisException:", ex);
        return result;
    }

    /**
     * 截获其他异常
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-26
     */
    @ExceptionHandler(Exception.class)
    public ActionResult handleServerErrorException(Exception ex) {
        ActionResult result = ActionResult.result();

        Throwable cause = ex.getCause();
        if (cause != null && cause instanceof BusinessException) {
            BusinessException e = (BusinessException) cause;
            result.fail(e.getCode(), e.getMessage());
        } else {
            result.fail(ServiceError.SERVER_ERROR, ex.getMessage());
        }
        log.error("ServerException:", ex);
        return result;
    }
}
