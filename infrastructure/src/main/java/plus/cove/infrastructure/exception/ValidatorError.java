package plus.cove.infrastructure.exception;

/**
 * 验证错误
 * code 范围：10-19
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum ValidatorError implements BusinessError {
    INVALID_CONSTRAINT(100, "无效的验证规则"),
    INVALID_ARGUMENT(101, "无效的参数"),
    IDEMPOTENT_TOKEN_MISSING(110, "无效的提交参数"),
    IDEMPOTENT_TOKEN_DUPLICATED(111, "重复提交数据");


    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;

    ValidatorError(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

