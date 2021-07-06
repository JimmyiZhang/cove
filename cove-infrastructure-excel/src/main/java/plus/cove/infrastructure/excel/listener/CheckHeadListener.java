package plus.cove.infrastructure.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import lombok.extern.slf4j.Slf4j;
import plus.cove.infrastructure.excel.importable.Importable;
import plus.cove.infrastructure.exception.BusinessException;
import plus.cove.infrastructure.exception.ConverterError;
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
    private final Importable identity;
    private final Boolean emptyStop;

    public CheckHeadListener(Importable identity) {
        this(identity, true);
    }

    public CheckHeadListener(Importable identity, Boolean emptyStop) {
        this.identity = identity;
        this.emptyStop = emptyStop;
    }

    @Override
    public void onException(Exception ex, AnalysisContext context) {
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        ReadRowHolder holder = context.readRowHolder();
        if (this.identity.getMaxRowIndex() == null || holder == null) {
            return;
        }

        // 判断最大行
        if (IntegerHelper.greaterThan(holder.getRowIndex(), this.identity.getMaxRowIndex())) {
            throw new BusinessException(ConverterError.INVALID_IMPORT_DATA_ROW, this.identity.getMaxRowError());
        }
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
    }

    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        if (this.identity.getIdentityColumn() == null) {
            return;
        }

        // 判断验证规则
        CellData cell = headMap.get(this.identity.getIdentityColumn());
        if (cell == null || !cell.getStringValue().equals(this.identity.getIdentityValue())) {
            throw new BusinessException(ConverterError.INVALID_IMPORT_TEMPLATE);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        ReadRowHolder holder = context.readRowHolder();
        if (this.identity.getMaxColumnIndex() == null || holder == null) {
            return;
        }

        // 判断最大列
        if (IntegerHelper.greaterThan(holder.getCellMap().size(), this.identity.getMaxColumnIndex())) {
            throw new BusinessException(ConverterError.INVALID_IMPORT_DATA_ROW, this.identity.getMaxColumnError());
        }
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        ReadRowHolder holder = context.readRowHolder();
        if (holder == null) {
            return false;
        }

        if (emptyStop.booleanValue()) {
            return holder.getCellMap().size() > 0;
        }

        return true;
    }
}