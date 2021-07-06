package plus.cove.infrastructure.exception;

/**
 * 验证错误
 * 20-29
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public enum ConverterError implements BusinessError {
    INVALID_ORIGIN(120, "无效的来源数据"),
    INVALID_TARGET(121, "无效的目标数据"),
    INVALID_CLASS_TYPE(122, "无效的类型类别"),

    INVALID_IMPORT_EMPTY(123,"上传数据为空"),
    INVALID_IMPORT_TEMPLATE(124, "请使用在本系统下载的模板进行上传，否则无法识别"),
    INVALID_IMPORT_DATA_ROW(125, "上传数据超过最大限制，请确认是否超过最大行数");


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
    ConverterError(final int code, final String message) {
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

