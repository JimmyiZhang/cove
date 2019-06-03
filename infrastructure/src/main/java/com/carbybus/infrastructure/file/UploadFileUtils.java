package com.carbybus.infrastructure.file;

import com.carbybus.infrastructure.exception.BusinessException;
import com.carbybus.infrastructure.exception.FileError;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;

import java.io.*;
import java.net.MalformedURLException;
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
    private static final Logger log = LoggerFactory.getLogger(UploadFileUtils.class);
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
    public static UploadFileResult saveFile(String rootPath, String fileName, InputStream fileStream) {
        UploadFileResult result = new UploadFileResult();
        result.setName(fileName);

        // 绝对路径
        String absolutePath = Paths.get(rootPath).toAbsolutePath().toString();
        // 日期路径
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String datePath = LocalDate.now().format(formatter);

        // 文件目录
        Path filePath = Paths.get(absolutePath).resolve(datePath);

        // 文件路径
        String realName = getRealName();
        String extendName = getExtendName(fileName);

        String fullName = getFullName(realName, extendName, null);
        Path fullPath = filePath.resolve(fullName);
        String thumName = getFullName(realName, extendName, "_t");
        Path thumPath = filePath.resolve(thumName);


        ByteArrayOutputStream bufferStream = null;
        InputStream oriStream = null;
        InputStream thumStream = null;
        // 创建文件
        try {
            bufferStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[fileStream.available()];
            int len;
            while ((len = fileStream.read(buffer)) > -1) {
                bufferStream.write(buffer, 0, len);
            }
            bufferStream.flush();

            if (!Files.exists(filePath)) {
                Files.createDirectory(filePath);
            }

            oriStream = new ByteArrayInputStream(bufferStream.toByteArray());
            thumStream = new ByteArrayInputStream(bufferStream.toByteArray());
            // 保存源文件
            Files.copy(oriStream, fullPath, StandardCopyOption.REPLACE_EXISTING);

            // 保存缩略图
            Thumbnails.of(thumStream)
                    .size(60, 60)
                    .toFile(thumPath.toString());
        } catch (IOException ex) {
            log.error(FileError.CREATE_ERROR.toString(), ex);
            throw new BusinessException(FileError.CREATE_ERROR);
        } finally {
        }

        String realPath = Paths.get(datePath).resolve(fullName).toString();
        result.setUrl(realPath);
        return result;
    }

    /**
     * 获取文件
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-31
     */
    public static Resource loadFile(String rootPath, String fileName) {
        Path filePath = Paths.get(rootPath).toAbsolutePath().resolve(fileName);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            return resource;
        } catch (MalformedURLException ex) {
            log.error(FileError.NOT_FOUND.toString(), ex);
            throw new BusinessException(FileError.NOT_FOUND);
        }
    }

    /**
     * 根据扩展名获取媒体类型
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-31
     */
    public static MediaType getMediaType(String fileName) {
        MediaType type = MediaType.ALL;
        String exName = getExtendName(fileName);
        switch (exName) {
            case "jpeg":
            case "jpg":
                type = MediaType.IMAGE_JPEG;
                break;
            case "png":
                type = MediaType.IMAGE_PNG;
                break;
            case "gif":
                type = MediaType.IMAGE_GIF;
                break;
        }
        return type;
    }

    /**
     * 获取文件扩展名
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-31
     */
    private static String getExtendName(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    /**
     * 生成真实不重复的名字
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-03
     */
    private static String getRealName() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取完成文件名
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-03
     */
    private static String getFullName(String fileName, String extendName, String suffix) {
        StringBuilder sbName = new StringBuilder();
        sbName.append(fileName);
        if (!StringUtils.isEmpty(suffix)) {
            sbName.append(suffix);
        }
        if (!StringUtils.isEmpty(extendName)) {
            sbName.append(".");
            sbName.append(extendName);
        }

        return sbName.toString();
    }
}
