package plus.cove.infrastructure.system;

import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统配置
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Component
public class SystemUtils {
    @Autowired
    UniteSystemConfig systemConfig;

    /**
     * 生成编码
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public String generateWordCode(String actualCode) {
        return systemConfig.getRunTime() == SystemRunTime.DEVELOPMENT ? systemConfig.getAuthCode() : actualCode;
    }

    /**
     * 随机认证码
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public String generateNumberCode(final int count) {
        String actual = RandomUtil.randomNumbers(count);
        return generateWordCode(actual);
    }

    /**
     * 随机认证码
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public String generateWordCode(final int count) {
        String actual = RandomUtil.randomString(count);
        return generateWordCode(actual);
    }
}
