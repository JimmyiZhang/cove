package plus.cove.infrastructure.excel.importable;

/**
 * 可以导入
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class DefaultImportConstraint implements Importable {
    private Integer maxRowIndex;
    private String maxRowError;
    private Integer maxColumnIndex;
    private String maxColumnError;
    private Integer identityColumn;
    private String identityValue;

    public DefaultImportConstraint(
            Integer rowIndex,
            String rowsError,
            Integer columnIndex,
            String columnError) {
        this.maxRowIndex = rowIndex;
        this.maxRowError = rowsError;
        this.maxColumnIndex = columnIndex;
        this.maxColumnError = columnError;
    }

    public DefaultImportConstraint(
            Integer rowIndex,
            Integer columnIndex) {
        this(rowIndex, "超过最大可解析行数: " + rowIndex.toString(),
                columnIndex, "超过最大可解析列数: " + columnIndex.toString());
    }

    @Override
    public String getMaxRowError() {
        return maxRowError;
    }

    @Override
    public Integer getMaxRowIndex() {
        return maxRowIndex;
    }

    @Override
    public Integer getMaxColumnIndex() {
        return maxColumnIndex;
    }

    @Override
    public String getMaxColumnError() {
        return maxColumnError;
    }

    @Override
    public Integer getIdentityColumn() {
        return identityColumn;
    }

    @Override
    public String getIdentityValue() {
        return identityValue;
    }
}
