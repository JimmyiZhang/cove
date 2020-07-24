package plus.cove.infrastructure.file;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.GpsDirectory;
import org.springframework.http.MediaType;
import plus.cove.infrastructure.exception.BusinessException;
import plus.cove.infrastructure.exception.FileError;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plus.cove.infrastructure.utils.DateTimeHelper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * 上传文件
 *
 * @author jimmy.zhang
 * @date 2019-05-31
 */
public class UploadFileUtils {
    private static final Logger log = LoggerFactory.getLogger(UploadFileUtils.class);

    private UploadFileUtils() {
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
        String exName = FileUtils.getExtendName(fileName);
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
                    meta.setToken(DateTimeHelper.fromDate(token));
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String datePath = LocalDate.now().format(formatter);

        // 文件目录
        Path filePath = absolutePath.resolve(datePath);

        // 文件路径
        String realName = UUID.randomUUID().toString();
        String extendName = FileUtils.getExtendName(fileName);

        String fullName = FileUtils.getFullName(realName, extendName, null);
        Path fullPath = filePath.resolve(fullName);
        String thumName = FileUtils.getFullName(realName, extendName, "_t");
        Path thumPath = filePath.resolve(thumName);

        InputStream oriStream = null;
        InputStream thumStream = null;

        log.info("upload file path: {}", fullPath);
        // 创建文件
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectory(filePath);
            }

            ByteArrayOutputStream bufferStream = FileUtils.copyStream(fileStream);
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
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Thumbnails.of(filePath.toString())
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
