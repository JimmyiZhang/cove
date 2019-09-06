package com.carbybus.infrastructure.file;

import com.carbybus.infrastructure.exception.BusinessException;
import com.carbybus.infrastructure.exception.FileError;
import com.carbybus.infrastructure.utils.DateTimeUtils;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.GpsDirectory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * 文件工具
 *
 * @author jimmy.zhang
 * @date 2019-06-04
 */
public class FileUtils {
    private static final Logger log = LoggerFactory.getLogger(UploadFileUtils.class);

    private FileUtils() {
    }

    /**
     * 获取文件绝对路径
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-04
     */
    public static Path getAbsolutePath(String rootPath) {
        return Paths.get(rootPath).toAbsolutePath();
    }

    /**
     * 生成真实不重复的名字
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-03
     */
    public static String getRealName() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取文件扩展名
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-31
     */
    public static String getExtendName(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    /**
     * 获取完成文件名
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-03
     */
    public static String getFullName(String fileName, String extendName, String suffix) {
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
     * 拷贝到缓存
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-04
     */
    public static ByteArrayOutputStream copyCache(InputStream stream) throws IOException {
        ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[stream.available()];
        int len;
        while ((len = stream.read(buffer)) > -1) {
            bufferStream.write(buffer, 0, len);
        }
        bufferStream.flush();
        return bufferStream;
    }

    /**
     * 获取文件元信息
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-04
     */
    public static FileMetadata getFileMetadata(UploadFileResult result) {
        FileMetadata meta = new FileMetadata();
        meta.setUrl(result.getUrl());
        meta.setName(result.getName());
        try {
            Metadata fileMeta = ImageMetadataReader.readMetadata(result.getFullPath().toFile());
            GpsDirectory gpsDirectory = fileMeta.getFirstDirectoryOfType(GpsDirectory.class);
            if (gpsDirectory != null) {
                GeoLocation geo = gpsDirectory.getGeoLocation();
                meta.setLatitude(geo.getLatitude());
                meta.setLongitude(geo.getLongitude());
            }

            ExifIFD0Directory exifDirectory = fileMeta.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (exifDirectory != null) {
                meta.setMake(exifDirectory.getString(ExifDirectoryBase.TAG_MAKE));
                meta.setModel(exifDirectory.getString(ExifDirectoryBase.TAG_MODEL));

                Date token = exifDirectory.getDate(ExifDirectoryBase.TAG_DATETIME);
                if (token != null) {
                    meta.setToken(DateTimeUtils.fromDate(token));
                }
            }
        } catch (ImageProcessingException ex) {
            log.error(FileError.CREATE_ERROR.toString(), ex);
            throw new BusinessException(FileError.CREATE_ERROR);
        } catch (IOException ex) {
            log.error(FileError.CREATE_ERROR.toString(), ex);
            throw new BusinessException(FileError.CREATE_ERROR);
        }

        return meta;
    }
}
