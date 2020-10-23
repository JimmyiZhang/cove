package plus.cove.infrastructure.component;

import java.util.List;

/**
 * 仓储基类
 * 满足访问存储的要求：
 * 1. 常用的增删改查操作
 * 2. 特例方法通过自定义结合MyBatis实现
 * 3. 可扩展（软删除，读写分离等）
 *
 * @author jimmy.zhang
 * @date 2019-03-07
 */
public interface BaseRepository<T> {
    /**
     * 根据条件获取实体集合
     *
     * @param entity 条件
     * @return 实体集合
     * @author jimmy.zhang
     * @since 1.0
     */
    List<T> select(T entity);

    /**
     * 获取所有实体
     *
     * @param
     * @return 实体集合
     * @author jimmy.zhang
     * @since 1.0
     */
    List<T> selectAll();

    /**
     * 根据条件获取一条实体
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    T selectOne(T entity);

    /**
     * 根据主键获取实体
     *
     * @param id 主键
     * @return 实体
     * @author jimmy.zhang
     * @since 1.0
     */
    T selectById(Object id);

    /**
     * 根据实体获取数量
     *
     * @param entity 实体
     * @return 数量
     * @author jimmy.zhang
     * @since 1.0
     */
    int selectCount(T entity);

    /**
     * 插入实体
     * null不插入
     *
     * @param entity 实体
     * @return 影响行数
     * @author jimmy.zhang
     * @since 1.0
     */
    int insert(T entity);

    /**
     * 插入实体
     * null插入
     *
     * @param entity 实体
     * @return 影响行数
     * @author jimmy.zhang
     * @since 1.0
     */
    int insertAll(T entity);

    /**
     * 根据主键更新实体
     * null不更新
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    int updateById(T entity);

    /**
     * 根据主键更新实体
     * null更新
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    int updateAllById(T entity);

    /**
     * 根据条件删除实体
     *
     * @param entity 实体
     * @return 影响行数
     * @author jimmy.zhang
     * @since 1.0
     */
    int delete(T entity);

    /**
     * 根据主键删除实体
     *
     * @param id 主键
     * @return 影响行数
     * @author jimmy.zhang
     * @since 1.0
     */
    int deleteById(Object id);
}
