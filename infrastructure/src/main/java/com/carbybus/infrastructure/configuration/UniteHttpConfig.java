package com.carbybus.infrastructure.configuration;

import com.google.common.base.Splitter;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * http配置
 *
 * @author jimmy.zhang
 * @ 2019-04-28
 */
public class UniteHttpConfig {
    /**
     * 分隔符
     */
    private final static String SEPARATOR = ",";

    /**
     * 缓存时间，默认10分钟
     */
    @Getter
    @Setter
    private long cacheMaxAge = 10 * 60L;

    /**
     * 跨域允许的源地址
     * 不含http://
     */
    @Setter
    private String corsAllowedOrigins = "*";

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
        if (StringUtils.isEmpty(corsAllowedOrigins)) {
            return new String[]{"*"};
        }

        List<String> origins = Splitter.on(SEPARATOR)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(this.corsAllowedOrigins);

        String[] originsArr = new String[origins.size()];
        origins.toArray(originsArr);

        return originsArr;
    }

    /**
     * 跨域允许的头
     */
    @Setter
    private String corsAllowedHeaders = "*";

    /**
     * 获取跨域请求头
     *
     * @param
     * @return 跨域请求头数组，不设置则为*
     * @author jimmy.zhang
     * @date 2019-04-28
     */
    public String[] getCorsAllowedHeaders() {
        if (StringUtils.isEmpty(corsAllowedHeaders)) {
            return new String[]{"*"};
        }

        List<String> origins = Splitter.on(SEPARATOR)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(this.corsAllowedHeaders);

        String[] originsArr = new String[origins.size()];
        origins.toArray(originsArr);

        return originsArr;
    }

    @Setter
    private String corsAllowedMethods = "*";

    /**
     * 获取跨域请求方法
     *
     * @param
     * @return 跨域请求头数组，不设置则为*
     * @author jimmy.zhang
     * @date 2019-04-28
     */
    public String[] getCorsAllowedMethods() {
        if (StringUtils.isEmpty(corsAllowedMethods)) {
            return new String[]{"*"};
        }

        List<String> origins = Splitter.on(SEPARATOR)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(this.corsAllowedMethods);

        String[] originsArr = new String[origins.size()];
        origins.toArray(originsArr);

        return originsArr;
    }
}
