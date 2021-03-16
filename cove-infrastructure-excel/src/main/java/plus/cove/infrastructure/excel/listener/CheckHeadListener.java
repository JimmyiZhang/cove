package plus.cove.infrastructure.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;
import plus.cove.infrastructure.excel.exception.ExcelError;
import plus.cove.infrastructure.excel.importable.Importable;
import plus.cove.infrastructure.exception.BusinessException;
import plus.cove.infrastructure.utils.IntegerHelper;

import java.util.Map;

/**
 * 读取异常
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Slf4j
public class CheckHeadListener<T> implements ReadListener<T> {
    private final Integer maxRowIdx;
    private final String maxRowErr;
    private final Integer identityCol;
    private final String identityVal;

    public CheckHeadListener(Importable identity) {
        this.identityCol = identity.getIdentityColumn();
        this.identityVal = identity.getIdentityValue();
        this.maxRowIdx = identity.getMaxRowIndex();
        this.maxRowErr = identity.getMaxRowError();
    }

    @Override
    public void onException(Exception ex, AnalysisContext context) {
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        Integer rowIndex = context.readRowHolder().getRowIndex();
        if (IntegerHelper.greaterThan(rowIndex, maxRowIdx)) {
            throw new BusinessException(ExcelError.INVALID_IMPORT_DATA_ROW, this.maxRowErr);
        }
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
    }

    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        CellData cell = headMap.get(this.identityCol);
        if (cell == null || !cell.getStringValue().equals(this.identityVal)) {
            throw new BusinessException(ExcelError.INVALID_IMPORT_TEMPLATE);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        boolean hasData = context.readRowHolder().getCellMap().size() > 0;
        return hasData;
    }
}
