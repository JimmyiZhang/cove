package plus.cove.infrastructure.component;

import java.io.Serializable;
import java.util.Collection;

/**
 * 应用基类
 * 实现基本应用操作
 * 对外提供各种应用功能，
 * 对内调用领域完成业务逻辑
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public interface BaseApplication<T> {
    /**
     * <p>
     * 保存实体
     * </p>
     *
     * @param entity 实体对象
     * @return true-保存成功
     */
    ActionResult save(T entity);

    /**
     * <p>
     * 批量保存实体
     * </p>
     *
     * @param entityList 实体对象集合
     * @return true-保存成功
     */
    ActionResult saveBatch(Collection<T> entityList);

    /**
     * <p>
     * 根据id删除实体
     * </p>
     *
     * @param id 主键id
     * @return true-删除成功
     */
    ActionResult removeById(Serializable id);

    /**
     * <p>
     * 根据id更新实体
     * </p>
     *
     * @param entity 实体对象
     * @return true-更新成功
     */
    ActionResult updateById(T entity);

    /**
     * <p>
     * 根据id获取实体
     * </p>
     *
     * @param id 主键ID
     * @return 主键对应的实体，没有返回 null
     */
    T getById(Serializable id);
}
