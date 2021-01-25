package plus.cove.infrastructure.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.regex.Pattern;

/**
 * 时间转换器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class RichTextConverter implements Converter<String> {
    private final static String DEFAULT_EMPTY_VALUE = "";
    private final static String HTML_REGEX = "<[^>]+>";

    @Override
    public Class supportJavaTypeKey() {
        return String.class;
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
    public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {
        // 不支持集合读取
        return cellData.getStringValue();
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
    public CellData convertToExcelData(String sourceData, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        if (sourceData == null) {
            return new CellData(DEFAULT_EMPTY_VALUE);
        }

        Pattern htmlPattern = Pattern.compile(HTML_REGEX, Pattern.CASE_INSENSITIVE);
        String cellData = htmlPattern.matcher(sourceData).replaceAll("");
        return new CellData(cellData);
    }
}