package plus.cove.infrastructure.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间转换器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class LocalDateTimeConverter implements Converter<LocalDateTime> {
    private final static String DEFAULT_EMPTY_VALUE = "-";
    private final static String DEFAULT_DATE_FORMAT = "yyyy-m-d HH:mm";

    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
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
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                           GlobalConfiguration globalConfiguration) {
        if (cellData == null) {
            return null;
        }

        if (cellData.getType() == CellDataTypeEnum.STRING) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
            return LocalDateTime.parse(cellData.getStringValue(), formatter);
        }

        return null;
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
    public CellData convertToExcelData(LocalDateTime sourceData, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        if (sourceData == null) {
            return new CellData(DEFAULT_EMPTY_VALUE);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        String cellData = sourceData.format(formatter);

        return new CellData(cellData);
    }
}