package plus.cove.infrastructure.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

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

}
