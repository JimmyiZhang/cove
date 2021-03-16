package plus.cove.infrastructure.exception;

/**
 * 缓存异常
 *
 * @author jimmy.zhang
 * @date 2019-05-31
 */
public enum CacheError implements BusinessError {
    NOT_FOUND(130, "文件未找到"),
    CREATE_ERROR(301, "创建失败"),
    READ_ERROR(302, "读取失败"),
    WRITE_ERROR(303, "写失败");

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
    CacheError(final int code, final String message) {
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