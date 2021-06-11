package plus.cove.jazzy.domain.exception;

import plus.cove.infrastructure.exception.BusinessError;

/**
 * 账号错误
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum AccountError implements BusinessError {
    EXISTED_ACCOUNT(10, "该账号已存在"),
    INVALID_NAME(11, "无效的账号"),
    INVALID_PASSWORD(12, "无效的密码"),
    INVALID_STATUS(13, "无效的状态或已过期"),

    INVALID_ACTIVATION(20, "无效的激活码"),
    EXPIRED_ACTIVATION(21, "激活码已过期"),
    USED_ACTIVATION(22, "账号已激活"),

    INVALID_LIMIT(30, "超过登陆错误上限"),
    INVALID_VERSION(31, "无效的随机码");

    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;

    private static final int CATEGORY = 1000;

    /**
     * 构造函数
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-03-08
     */
    AccountError(final int code, final String message) {
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
