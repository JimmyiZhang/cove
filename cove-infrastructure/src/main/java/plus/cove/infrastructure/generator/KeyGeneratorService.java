package plus.cove.infrastructure.generator;

import java.time.LocalTime;

/**
 * 主键服务
 * <p>
 * 根据当前时间
 *
 * @author jimmy.zhang
 * @since 2.0
 */
public class KeyGeneratorService {
    private KeyGeneratorService() {
    }

    /**
     * 获取当前毫秒
     *
     * @return 当前毫秒
     */
    public static long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取一天内当前秒数
     *
     * @return 当前秒数
     */
    public static int getSecondsInDay() {
        LocalTime now = LocalTime.now();
        return now.getHour() * 60 * 60 + now.getMinute() * 60 + now.getSecond();
    }
}
