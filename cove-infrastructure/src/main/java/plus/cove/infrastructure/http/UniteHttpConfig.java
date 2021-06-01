package plus.cove.infrastructure.http;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * http配置
 *
 * @author jimmy.zhang
 * @ 2019-04-28
 */
@Configuration
@ConfigurationProperties("summer.http-config")
public class UniteHttpConfig {
    /**
     * 分隔符
     */
    private static final String SEPARATOR_ALLOWED = ",";

    /**
     * 全部允许
     */
    private static final String WHOLE_ALLOWED = "*";

    /**
     * 连接时间，单位秒
     * 默认30s
     */
    @Getter
    @Setter
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration connectTimeout = Duration.ofSeconds(30L);

    /**
     * 读取时间，单位秒
     * 默认300s
     */
    @Getter
    @Setter
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration readTimeout = Duration.ofSeconds(300L);

    /**
     * 跨域预检有效期，单位秒
     * 默认1天
     */
    @Getter
    @Setter
    private Duration corsMaxAge = Duration.ofDays(1L);

    /**
     * 跨域允许的源地址
     * 不含http://
     */
    @Setter
    private String corsAllowedOrigins;

    /**
     * 获取跨域请求域
     * 包括协议
     *
     * @param
     * @return 跨域请求域数组，不设置则为*
     * @author jimmy.zhang
     * @date 2019-04-28
     */
    public String[] getCorsAllowedOrigins() {
        if (StringUtils.isEmpty(corsAllowedOrigins) || WHOLE_ALLOWED.equals(corsAllowedOrigins)) {
            return new String[]{WHOLE_ALLOWED};
        }

        return corsAllowedOrigins.split(SEPARATOR_ALLOWED);
    }

    /**
     * 跨域允许的头
     */
    @Setter
    private String corsAllowedHeaders;

    /**
     * 获取跨域请求头
     *
     * @param
     * @return 跨域请求头数组，不设置则为*
     * @author jimmy.zhang
     * @date 2019-04-28
     */
    public String[] getCorsAllowedHeaders() {
        if (StringUtils.isEmpty(corsAllowedHeaders) || WHOLE_ALLOWED.equals(corsAllowedHeaders)) {
            return new String[]{WHOLE_ALLOWED};
        }

        return corsAllowedHeaders.split(SEPARATOR_ALLOWED);
    }

    @Setter
    private String corsAllowedMethods;

    /**
     * 获取跨域请求方法
     *
     * @param
     * @return 跨域请求头数组，不设置则为*
     * @author jimmy.zhang
     * @date 2019-04-28
     */
    public String[] getCorsAllowedMethods() {
        if (StringUtils.isEmpty(corsAllowedMethods) || WHOLE_ALLOWED.equals(corsAllowedMethods)) {
            return new String[]{WHOLE_ALLOWED};
        }

        return corsAllowedMethods.split(SEPARATOR_ALLOWED);
    }

    /**
     * 请求接口版本标识
     * <p>
     * 服务端接口版本
     */
    @Getter
    @Setter
    private String serverVersion = "Api-Version";

    /**
     * 请求产品版本标识
     * <p>
     * 客户端版本
     */
    @Getter
    @Setter
    private String clientVersion = "Product-Version";
}
