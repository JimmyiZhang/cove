package plus.cove.service.document.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文档配置
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
@Data
@Configuration
@ConfigurationProperties("document.excel")
public class DocumentExcelConfig {

}
