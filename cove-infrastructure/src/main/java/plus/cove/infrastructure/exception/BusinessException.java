package plus.cove.infrastructure.exception;

import lombok.Getter;

/**
 * <p>
 * 业务异常
 * </p>
 * <p>
 * 所有业务异常的基类
 *
 * @author jimmy.zhang
 * @date 2019-02-28
 */
public class BusinessException extends RuntimeException {
    /**
     * 编码，唯一编码
     */
    @Getter
    private final int code;

    /**
     * 信息，错误信息
     */
    private final String message;

    public BusinessException(BusinessError error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public BusinessException(BusinessError error, Throwable cause) {
        super(cause);
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public BusinessException(BusinessError error, String message) {
        this.code = error.getCode();
        this.message = message;
    }

    /**
     * 从错误获取异常信息
     *
     * @param error 异常枚举
     * @return
     * @author jimmy.zhang
     * @date 2019-03-29
     */
    public static BusinessException from(BusinessError error) {
        return new BusinessException(error);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("%s: code=%d, message=%s",
                this.getClass().getName(),
                this.code,
                this.message == null ? "无" : this.message);
    }
}
