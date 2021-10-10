package plus.cove.infrastructure.utils;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


/**
 * 文件工具
 *
 * @author jimmy.zhang
 * @date 2019-06-04
 */
public class FileHelper {
    private static final Logger log = LoggerFactory.getLogger(FileHelper.class);

    private FileHelper() {
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
     * 获取文件名
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-03
     */
    public static String getFileName(String path) {
        path = path.replace('\\', '/');
        int index = path.lastIndexOf("/");
        if (index > -1) {
            return path.substring(index + 1);
        }

        return path;
    }

    /**
     * 生成随机名称
     *
     * @return
     */
    public static String getRandomName(String path, String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return path + File.separator + UUID.randomUUID().toString();
        } else {
            return path + File.separator + UUID.randomUUID().toString() + fileName.substring(dotIndex);
        }
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
        if (!StrUtil.isEmpty(suffix)) {
            sbName.append(suffix);
        }
        if (!StrUtil.isEmpty(extendName)) {
            sbName.append(".");
            sbName.append(extendName);
        }

        return sbName.toString();
    }

    /**
     * 拷贝流
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-04
     */
    public static ByteArrayOutputStream copyStream(InputStream stream) throws IOException {
        ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[stream.available()];
        int len;
        while ((len = stream.read(buffer)) > -1) {
            bufferStream.write(buffer, 0, len);
        }
        bufferStream.flush();
        stream.close();
        return bufferStream;
    }

    /**
     * 拷贝流到文件
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static File copyFile(InputStream stream, Path path) throws IOException {
        File file = path.toFile();
        if (file.exists()) {
            return file;
        }

        File absFile = file.getParentFile();
        if (!absFile.exists()) {
            absFile.mkdir();
        }

        Files.copy(stream, file.toPath());
        return file;
    }
}
