package plus.cove.infrastructure.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import plus.cove.infrastructure.excel.exception.ExcelError;
import plus.cove.infrastructure.excel.importable.Importable;
import plus.cove.infrastructure.exception.BusinessException;

import java.util.Map;

/**
 * 读取异常
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class CheckHeadListener<T> implements ReadListener<T> {
    private final Integer identityCol;
    private final String identityVal;

    public CheckHeadListener(Importable identity) {
        this.identityCol = identity.getIdentityColumn();
        this.identityVal = identity.getIdentityValue();
    }

    @Override
    public void onException(Exception ex, AnalysisContext context) {
    }

    @Override
    public void invoke(T data, AnalysisContext context) {

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
        // 过滤空行
        boolean hasData = context.readRowHolder().getCellMap().size() > 0;
        return hasData;
    }
}
