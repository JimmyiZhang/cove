package plus.cove.infrastructure.exception;

/**
 * 网络异常
 * 包括邮件
 *
 * @author jimmy.zhang
 * @date 2019-05-31
 */
public enum NetworkError implements BusinessError {
    MAIL_SUBJECT_ERROR(310, "无效的主题"),
    MAIL_FROM_ERROR(311, "无效的发件人"),
    MAIL_TO_ERROR(312, "无效的收件人"),
    MAIL_CONTENT_ERROR(313, "邮件内容无效"),
    MAIL_SEND_ERROR(314, "邮件发送失败");

    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;

    /**
     * @description: 构造函数
     * @param:
     * @return:
     * @author: jimmy.zhang
     * @date: 2019-03-08
     */
    NetworkError(final int code, final String message) {
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