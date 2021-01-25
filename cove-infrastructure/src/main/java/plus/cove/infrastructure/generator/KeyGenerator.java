package plus.cove.infrastructure.generator;

/**
 * 主键生成器
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
public interface KeyGenerator {
    /**
     * 获取类型
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-09-06
     */
    String getType();

    /**
     * 生成键
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-09-06
     */
    long generateKey();
}
