package plus.cove.infrastructure.generator;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 主键构造器
 * <p>
 * 用来生成主键，主键保持递增（非连续）
 * 使用方法：KeyGeneratorBuilder.INSTANCE.build()
 * <p>
 * 多机器可以通过在环境变量通过
 * summer.generator.work-id设置work id
 *
 * @author jimmy.zhang
 * @since 1.1
 */
public class KeyGeneratorBuilder {
    private final Logger log = LoggerFactory.getLogger(KeyGeneratorBuilder.class);

    private static final String PROP_WORK_ID = "summer.generator.worker-id";
    private KeyGenerator generator;

    /**
     * 单例模式
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-09-06
     */
    public static final KeyGeneratorBuilder INSTANCE = new KeyGeneratorBuilder();

    private KeyGeneratorBuilder() {
        int workId = 0;
        // 获取环境变量
        String prop = System.getProperty(PROP_WORK_ID);
        if (StrUtil.isEmpty(prop)) {
            log.warn("cove.infrastructure.generator - [{}] is not configured", PROP_WORK_ID);
        } else {
            workId = Integer.valueOf(prop).intValue();
            log.info("cove.infrastructure.generator - [{}] is {}", PROP_WORK_ID, workId);
        }
        this.generator = new SnowflakeKeyGenerator(workId);
    }

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-09-06
     */
    public long build() {
        return this.generator.generateKey();
    }
}
