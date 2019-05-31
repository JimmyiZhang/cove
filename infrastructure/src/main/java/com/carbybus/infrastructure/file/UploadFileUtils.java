package com.carbybus.infrastructure.file;

import com.carbybus.infrastructure.exception.BusinessException;
import com.carbybus.infrastructure.exception.FileError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.validation.ValidationUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
        int dotIndex = fileName.lastIndexOf('.');
        String realName = UUID.randomUUID().toString();
        String fullName = (dotIndex == -1) ? realName : realName + fileName.substring(dotIndex);
        Path fullPath = filePath.resolve(fullName);

        // 创建文件
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectory(filePath);
            }
            Files.copy(fileStream, fullPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            log.error(FileError.CREATE_ERROR.toString(), ex);
            throw new BusinessException(FileError.CREATE_ERROR);
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
    * @param  
    * @return  
    * @author jimmy.zhang 
    * @date 2019-05-31 
    */ 
    public static MediaType getMediaType(String fileName){
        MediaType type = MediaType.ALL;
        String exName = getFileExtension(fileName);
        switch (exName){
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
        return  type;
    }

    /** 
    * 获取文件扩展名 
    * @param  
    * @return  
    * @author jimmy.zhang 
    * @date 2019-05-31 
    */ 
    public static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
