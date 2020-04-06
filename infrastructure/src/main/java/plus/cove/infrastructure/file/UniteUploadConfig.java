package plus.cove.infrastructure.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

/**
 * 上传文件配置
 * 包括上传路径
 *
 * @author jimmy.zhang
 * @ 2019-04-28
 */
@Data
@Configuration
@ConfigurationProperties("summer.upload-config")
public class UniteUploadConfig {
    /**
     * 上传路径
     */
    private String uploadPath = "uploads";

    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize maxFileSize = DataSize.ofBytes(10240);
}
