package plus.cove.infrastructure.utils;

/**
 * 日志工具类
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @date 2019-07-31
 */
public class LoggerHelper {
    /**
     * 系统日志
     * 包括系统启停(info)、系统运行监控日志等(debug、每分钟)
     * 通过该日志可以知道系统是否顺利启动、开启debug日志后可以知晓当前系统整体状态
     */
    public static final String SYSTEM = "system";

    /**
     * 持久化日志
     * 主要是mongo/redis的数据存储、加载日志，较重要
     * 通过该日志可以知道缓存的加载时间，持续时间等
     */
    public static final String PERSISTENCE = "persistence";

    /**
     * 网络层日志
     * 主要包括request/response/notify等
     * 也包括自定义网络层的一些日志
     */
    public static final String NETWORK = "network";

    /**
     * 运维、运营扥管理日志
     * 如推送，调度等
     */
    public static final String MANAGEMENT = "management";

}
