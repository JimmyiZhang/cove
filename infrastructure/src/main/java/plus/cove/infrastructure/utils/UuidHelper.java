package plus.cove.infrastructure.utils;

import java.util.UUID;

/**
 * uuid帮助类
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class UuidHelper {
    private UuidHelper() {
    }

    /**
     * 生成32位uuid
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static String buildUuid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
