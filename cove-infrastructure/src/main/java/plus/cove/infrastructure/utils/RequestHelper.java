package plus.cove.infrastructure.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plus.cove.infrastructure.http.UniteHttpConfig;

import javax.servlet.http.HttpServletRequest;

/**
 * Http工具类
 *
 * @author jimmy.zhang
 * @date 2019-05-06
 */
@Component
public class RequestHelper {
    private final Logger log = LoggerFactory.getLogger(RequestHelper.class);

    private static final String IP_UNKNOWN = "unKnown";
    private static final String IP_LOCAL_V6 = "0:0:0:0:0:0:0:1";
    private static final String IP_LOCAL_V4 = "127.0.0.1";

    @Autowired
    UniteHttpConfig httpConfig;

    /**
     * 获取ip地址
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-19
     */
    public String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !IP_UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }

        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !IP_UNKNOWN.equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = request.getRemoteAddr();
        if (IP_LOCAL_V6.equals(ip)) {
            ip = IP_LOCAL_V4;
            return ip;
        }

        return ip;
    }

    /**
     * 获取当前请求版本号
     *
     * @return data source key
     */
    public String getClientVersion(HttpServletRequest request) {
        String code = request.getHeader(httpConfig.getClientVersion());
        if (StringUtils.isEmpty(code)) {
            code = request.getParameter(httpConfig.getClientVersion().toLowerCase());
        }
        return code;
    }
}
