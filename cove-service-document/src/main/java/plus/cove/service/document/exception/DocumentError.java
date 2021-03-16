package plus.cove.service.document.exception;

import plus.cove.infrastructure.exception.BusinessError;

/**
 * 文档错误
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public enum DocumentError implements BusinessError {
    EXCEL_EXPORT_FAILURE(10, "导出excel失败"),
    EXCEL_IMPORT_FAILURE(11, "解析excel失败");

    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;

    private static final int CATEGORY = 800;

    /**
     * 构造函数
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-03-08
     */
    DocumentError(final int code, final String message) {
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
