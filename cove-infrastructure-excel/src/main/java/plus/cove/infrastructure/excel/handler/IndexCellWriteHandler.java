package plus.cove.infrastructure.excel.handler;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 * 序号写入处理器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class IndexCellWriteHandler implements CellWriteHandler {
    private static final int ROW_BEGIN = 1;
    private static final String COLUMN_SIGN = "INDEX";

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                                 Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
                                Head head, Integer relativeRowIndex, Boolean isHead) {
    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                       CellData cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        // 针对行号进行处理
        if (!isHead && COLUMN_SIGN.equals(cellData.getStringValue())) {
            String rowIndex = String.valueOf(relativeRowIndex.intValue() + ROW_BEGIN);
            cellData.setStringValue(rowIndex);
        }
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
    }
}
