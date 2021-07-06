package plus.cove.infrastructure.excel.importable;

/**
 * 可以导入
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public interface Importable {
    /**
     * 获取最大行数
     *
     * @return
     */
    default Integer getMaxRowIndex() {
        return 500;
    }

    /**
     * 最大行数错误信息
     *
     * @return
     */
    default String getMaxRowError() {
        return "最大允许行数为500";
    }

    /**
     * 获取最大列数
     *
     * @return
     */
    default Integer getMaxColumnIndex() {
        return 50;
    }

    /**
     * 最大行数错误信息
     *
     * @return
     */
    default String getMaxColumnError() {
        return "最大允许列数为50";
    }

    /**
     * 获取标识列
     *
     * @return
     */
    Integer getIdentityColumn();

    /**
     * 获取标识值
     *
     * @return
     */
    String getIdentityValue();
}
