package plus.cove.infrastructure.retry;

import lombok.Getter;
import plus.cove.infrastructure.exception.ValidatorError;

/**
 * 重试异常
 * <p>
 * 用于重试失败抛出异常
 *
 * @author jimmy.zhang
 * @date 1.0
 */
public class RetryingException extends RuntimeException {
    /**
     * 编码，唯一编码
     */
    @Getter
    private final int code;

    /**
     * 信息，错误信息
     */
    @Getter
    private final String message;

    public RetryingException() {
        this.code = ValidatorError.RETRY_FAILURE.getCode();
        this.message = ValidatorError.RETRY_FAILURE.getMessage();
    }

    @Override
    public String toString() {
        return String.format("%s: code=%d, message=%s",
                this.getClass().getName(),
                this.code,
                this.message);
    }
}
