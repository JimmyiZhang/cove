package plus.cove.infrastructure.excel.exception;

import plus.cove.infrastructure.exception.BusinessError;

/**
 * Excel异常
 *
 * @author jimmy.zhang
 * @date 2019-05-31
 */
public enum ExcelError implements BusinessError {
    INVALID_IMPORT_TEMPLATE(600, "无法识别的导入模板"),
    INVALID_IMPORT_DATA_ROW(601, "上传数据超过最大限制，请确认是否超过最大行数");


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
    ExcelError(final int code, final String message) {
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