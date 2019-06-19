package com.carbybus.infrastructure.email;

import com.carbybus.infrastructure.exception.BusinessException;
import com.carbybus.infrastructure.exception.NetworkError;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * email配置
 *
 * @author jimmy.zhang
 * @ 2019-04-28
 */
@Data
@Configuration
@ConfigurationProperties("summer.email-config")
public class UniteEmailConfig {
    private String hostName;
    private Integer hostPort;
    private Boolean sslConnect;
    private String fromUser;
    private String userName;
    private String userPassword;

    /**
     * 检查配置文件
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-19
     */
    public void check() {
        if (this.hostName.isEmpty() ||
                this.hostPort == null ||
                this.sslConnect == null ||
                this.fromUser.isEmpty() ||
                this.userName == null ||
                this.userPassword == null) {
            throw new BusinessException(NetworkError.MAIL_CONTENT_ERROR);
        }
    }
}
