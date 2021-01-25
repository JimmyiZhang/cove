package plus.cove.infrastructure.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import plus.cove.infrastructure.excel.exportable.Exportable;

import java.util.Collection;

/**
 * 集合转换器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class CollectionConverter implements Converter<Collection> {
    private final static String DEFAULT_EMPTY_VALUE = "";
    private final static String DEFAULT_SEPARATE_VALUE = ",";

    @Override
    public Class supportJavaTypeKey() {
        return Collection.class;
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
    public Collection convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                        GlobalConfiguration globalConfiguration) {
        // 不支持集合读取
        throw new UnsupportedOperationException("不支持读取此类型");
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
    public CellData convertToExcelData(Collection sourceData, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        if (sourceData == null) {
            return new CellData(DEFAULT_EMPTY_VALUE);
        }

        StringBuilder cellBuilder = new StringBuilder(sourceData.size());
        for (Object data : sourceData) {
            if (data instanceof Exportable) {
                String ep = ((Exportable) data).export();
                cellBuilder.append(DEFAULT_SEPARATE_VALUE);
                cellBuilder.append(ep);
            }
        }

        String cellData = cellBuilder.length() > 0 ?
                cellBuilder.deleteCharAt(0).toString() : DEFAULT_EMPTY_VALUE;

        return new CellData(cellData);
    }
}