package plus.cove.infrastructure.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
}
