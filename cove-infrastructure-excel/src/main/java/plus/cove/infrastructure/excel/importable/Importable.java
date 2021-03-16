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
     * @return
     */
    Integer getMaxRowIndex();

    /**
     * 最大行数错误信息
     * @return
     */
    String getMaxRowError();

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
