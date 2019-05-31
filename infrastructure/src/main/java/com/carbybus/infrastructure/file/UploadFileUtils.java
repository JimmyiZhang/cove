package com.carbybus.infrastructure.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 上传文件
 *
 * @author jimmy.zhang
 * @date 2019-05-31
 */
public class UploadFileUtils {
    private static final String EXTENSION_SYMBOL = ".";

    private UploadFileUtils() {
    }

    /**
     * 获取文件路径
     * 生成带年月日且唯一的文件路径
     *
     * @param rootPath 根路径
     * @param fileName 文件名称
     * @return 生成后的文件名
     * @author jimmy.zhang
     * @date 2019-05-31
     */
    public static UploadFileResult saveFile(String rootPath, String fileName, InputStream fileStream) throws IOException {
        UploadFileResult result = new UploadFileResult();
        result.setName(fileName);

        String absolutePath = Paths.get(rootPath).toAbsolutePath().toString();
        // 默认按日期建文件夹
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String datePath = LocalDate.now().format(formatter);

        Path filePath = Paths.get(absolutePath).resolve(datePath);
        if (!Files.exists(filePath)) {
            Files.createDirectory(filePath);
        }

        // 获取扩展名
        int dotIndex = fileName.lastIndexOf('.');
        String realName = UUID.randomUUID().toString();
        String fullName = (dotIndex == -1) ? realName : realName + fileName.substring(dotIndex);

        Path fullPath = filePath.resolve(fullName);
        Files.copy(fileStream, fullPath, StandardCopyOption.REPLACE_EXISTING);

        String realPath = Paths.get(rootPath).resolve(datePath).resolve(fullName).toString();
        result.setUrl(realPath);
        return result;
    }
}
