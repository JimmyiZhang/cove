package com.carbybus.infrastructure.file;

import com.carbybus.infrastructure.exception.BusinessException;
import com.carbybus.infrastructure.exception.FileError;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
        // 绝对路径
        Path absolutePath = FileUtils.getAbsolutePath(rootPath);
        // 日期路径
        String datePath = FileUtils.getDateName();
        // 文件目录
        Path filePath = absolutePath.resolve(datePath);

        // 文件路径
        String realName = FileUtils.getRealName();
        String extendName = FileUtils.getExtendName(fileName);

        String fullName = FileUtils.getFullName(realName, extendName, null);
        Path fullPath = filePath.resolve(fullName);
        String thumName = FileUtils.getFullName(realName, extendName, "_t");
        Path thumPath = filePath.resolve(thumName);

        InputStream oriStream = null;
        InputStream thumStream = null;
        // 创建文件
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectory(filePath);
            }

            ByteArrayOutputStream bufferStream = FileUtils.copyCache(fileStream);
            oriStream = new ByteArrayInputStream(bufferStream.toByteArray());
            thumStream = new ByteArrayInputStream(bufferStream.toByteArray());

            // 保存源文件
            Files.copy(oriStream, fullPath, StandardCopyOption.REPLACE_EXISTING);

            // 保存缩略图
            Thumbnails.of(thumStream)
                    .size(60, 60)
                    .keepAspectRatio(false)
                    .toFile(thumPath.toString());
        } catch (IOException ex) {
            log.error(FileError.CREATE_ERROR.toString(), ex);
            throw new BusinessException(FileError.CREATE_ERROR);
        } finally {
            try {
                if (oriStream != null) {
                    oriStream.close();
                }
                if (thumStream != null) {
                    thumStream.close();
                }
            } catch (IOException ex) {
                log.warn(FileError.CREATE_ERROR.toString(), ex);
            }
        }

        UploadFileResult result = new UploadFileResult();
        result.setName(fileName);
        result.setFullPath(fullPath);

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
    public static byte[] loadFile(String rootPath, String fileName) {
        Path filePath = Paths.get(rootPath).toAbsolutePath().resolve(fileName);
        try {
            FileInputStream fileStream = new FileInputStream(filePath.toFile());
            byte[] fileByte = new byte[fileStream.available()];
            fileStream.read(fileByte, 0, fileStream.available());

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Thumbnails.of(fileStream)
                    .size(800, 800)
                    .toOutputStream(outStream);

            return outStream.toByteArray();
        } catch (FileNotFoundException ex) {
            log.error(FileError.NOT_FOUND.toString(), ex);
            throw new BusinessException(FileError.NOT_FOUND);
        } catch (IOException ex) {
            log.error(FileError.READ_ERROR.toString(), ex);
            throw new BusinessException(FileError.READ_ERROR);
        }
    }
}
