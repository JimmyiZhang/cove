package plus.cove.infrastructure.exception;

/**
 * 数据异常
 * 数据库相关
 *
 * @author jimmy.zhang
 * @date 2019-05-31
 */
public enum ConfigError implements BusinessError {
    EMPTY_DATASOURCE(200, "无效的激活码"),
    PRIMARY_DATASOURCE_HAS(201, "至少配置[primary]数据源"),
    PRIMARY_DATASOURCE_REMOVE(202, "不能移除[primary]数据源");

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
    ConfigError(final int code, final String message) {
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