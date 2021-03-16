package plus.cove.infrastructure.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * 序号转换器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class IndexConverter implements Converter<Long> {
    @Override
    public Class supportJavaTypeKey() {
        return Long.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 读取调用
     *
     * @param cellData            NotNull
     * @param contentProperty     Nullable
     * @param globalConfiguration NotNull
     * @return
     */
    @Override
    public Long convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                  GlobalConfiguration globalConfiguration) {
        return Long.parseLong(cellData.getStringValue());
    }

    /**
     * 写入调用
     *
     * @param sourceData          NotNull
     * @param contentProperty     Nullable
     * @param globalConfiguration NotNull
     * @return
     */
    @Override
    public CellData convertToExcelData(Long sourceData, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        CellData cell = new CellData();
        cell.setType(CellDataTypeEnum.STRING);
        cell.setStringValue("INDEX");
        return cell;
    }
}