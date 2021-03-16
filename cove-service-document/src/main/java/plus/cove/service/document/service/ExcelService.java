package plus.cove.service.document.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.cove.infrastructure.excel.handler.IndexCellWriteHandler;
import plus.cove.infrastructure.excel.importable.Importable;
import plus.cove.infrastructure.excel.listener.CheckHeadListener;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Excel服务
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Slf4j
@Service
public class ExcelService {
    @Autowired
    HttpServletResponse response;

    /**
     * 导出excel
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public void exportExcel(List<?> data, Class<?> dataType, String fileName) throws IOException {
        this.exportExcel(data, dataType, null, "sheet1", fileName);
    }

    /**
     * 导出excel
     *
     * @param
     * @param data
     * @param dataType
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public void exportExcel(List<?> data, Class<?> dataType, String sheetName, String fileName) throws IOException {
        this.exportExcel(data, dataType, null, sheetName, fileName);
    }

    /**
     * 导出excel
     *
     * @param
     * @param data
     * @param dataType
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public void exportExcel(List<?> data, Class<?> dataType, List<String> includeColumns, String sheetName, String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        // 处理中文乱码
        String name = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + name + ".xlsx");

        // 标题样式
        WriteCellStyle headerStyle = new WriteCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headerStyle.setWrapped(true);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        // 内容样式
        WriteCellStyle contentStyle = new WriteCellStyle();
        contentStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        contentStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);

        HorizontalCellStyleStrategy cellStyle = new HorizontalCellStyleStrategy(headerStyle, contentStyle);

        // 导出
        EasyExcel.write(response.getOutputStream(), dataType)
                .registerWriteHandler(cellStyle)
                .includeColumnFiledNames(includeColumns)
                .sheet(sheetName)
                .doWrite(data);
    }

    /**
     * 导出excel
     * 自带序号
     *
     * @param
     * @param data
     * @param dataType
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public void exportExcelWithIndex(List<?> data, Class<?> dataType, List<String> includeColumns, String sheetName, String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        // 处理中文乱码
        String name = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + name + ".xlsx");

        // 标题样式
        WriteCellStyle headerStyle = new WriteCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headerStyle.setWrapped(true);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        // 内容样式
        WriteCellStyle contentStyle = new WriteCellStyle();
        contentStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        contentStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);

        HorizontalCellStyleStrategy cellStyle = new HorizontalCellStyleStrategy(headerStyle, contentStyle);
        IndexCellWriteHandler indexHandler = new IndexCellWriteHandler();

        // 导出
        EasyExcel.write(response.getOutputStream(), dataType)
                .registerWriteHandler(cellStyle)
                .registerWriteHandler(indexHandler)
                .includeColumnFiledNames(includeColumns)
                .sheet(sheetName)
                .doWrite(data);
    }

    /**
     * 导入excel
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> List<T> importExcel(InputStream stream, Class dataType) {
        List<T> list = EasyExcel.read(stream)
                .head(dataType)
                .ignoreEmptyRow(false)
                .sheet(0)
                .doReadSync();

        return list;
    }

    /**
     * 导入excel
     * 带验证
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> List<T> importExcel(InputStream stream, Class dataType, Importable identity) {
        List<T> list = EasyExcel.read(stream, dataType, new CheckHeadListener(identity))
                .ignoreEmptyRow(false)
                .sheet(0)
                .doReadSync();

        return list;
    }
}
