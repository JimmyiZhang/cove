package plus.cove.jazzy.domain.account;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 用户激活配置
 *
 * @author jimmy.zhang
 * @date 2019-06-24
 */
@Data
@Configuration
@ConfigurationProperties("autumn.account-activation")
public class ActivationConfig {
    private Integer activeDays = 7;
    private String activeUrl = "";

    public String buildActiveUrl(String code) {
        return this.activeUrl + code;
    }
}
