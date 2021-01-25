package plus.cove.infrastructure.component;

/**
 * <p>
 * 基础实体
 * </p>
 * <p>
 * 仅包括主键定义
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public interface BaseEntity<T> {
    /**
     * 获取id
     *
     * @return T 返回对象
     * @author jimmy.zhang
     * @since 1.0
     */
    T getId();
}
