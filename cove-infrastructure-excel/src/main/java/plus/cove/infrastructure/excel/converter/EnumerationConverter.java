package plus.cove.infrastructure.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import plus.cove.infrastructure.component.BaseEnum;

/**
 * 枚举转换器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class EnumerationConverter implements Converter<BaseEnum> {
    private final static String DEFAULT_EMPTY_VALUE = "-";

    @Override
    public Class supportJavaTypeKey() {
        return BaseEnum.class;
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
    public BaseEnum convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                      GlobalConfiguration globalConfiguration) {
        // 不支持集合读取
        throw new UnsupportedOperationException("不支持读取此类型");
    }

    /**
     * 写入调用
     *
     * @param sourceData        NotNull
     * @param contentProperty     Nullable
     * @param globalConfiguration NotNull
     * @return
     */
    @Override
    public CellData convertToExcelData(BaseEnum sourceData, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        if (sourceData == null) {
            return new CellData(DEFAULT_EMPTY_VALUE);
        }

        String cellData = sourceData.getDesc();
        return new CellData(cellData);
    }
}