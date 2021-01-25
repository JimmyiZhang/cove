package plus.cove.jazzy.domain.exception;


import plus.cove.infrastructure.exception.BusinessError;

/**
 * 用户错误
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum TravellerError implements BusinessError {
    INVALID_USER(10, "无效的用户");

    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;

    private static final int CATEGORY = 4000;

    /**
     * 构造函数
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-03-08
     */
    TravellerError(final int code, final String message) {
        this.code = CATEGORY + code;
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
