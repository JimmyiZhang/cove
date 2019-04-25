package com.carbybus.infrastructure.component;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 应用基类
 * </p>
 * 实现基本应用操作
 * 对外提供各种应用功能，
 * 对内调用领域完成业务逻辑
 *
 * <p>
 * todo: 思考
 * 1. 基本类型是否需要暴露出去，还是作为内部属性供公共方法使用
 * 2. 转化操作在Application中还是在controller中
 * </p>
 *
 * @author jimmy.zhang
 * @date 2019-03-27
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
    boolean save(T entity);

    /**
     * <p>
     * 批量保存实体
     * </p>
     *
     * @param entityList 实体对象集合
     * @return true-保存成功
     */
    boolean saveBatch(Collection<T> entityList);

    /**
     * <p>
     * 根据id删除实体
     * </p>
     *
     * @param id 主键id
     * @return true-删除成功
     */
    boolean removeById(Serializable id);

    /**
     * <p>
     * 根据条件删除实体
     * </p>
     *
     * @param queryWrapper 实体对象操作
     * @return true-删除成功
     */
    boolean remove(Wrapper<T> queryWrapper);

    /**
     * <p>
     * 根据id更新实体
     * </p>
     *
     * @param entity 实体对象
     * @return true-更新成功
     */
    boolean updateById(T entity);

    /**
     * <p>
     * 根据条件更新
     * </p>
     *
     * @param updateWrapper 实体对象操作
     * @return true-更新成功
     */
    default boolean update(Wrapper<T> updateWrapper) {
        return update(null, updateWrapper);
    }

    /**
     * <p>
     * 根据条件更新实体
     * </p>
     *
     * @param entity        实体对象
     * @param updateWrapper 实体对象更新操作
     * @return true-更新成功
     */
    boolean update(T entity, Wrapper<T> updateWrapper);

    /**
     * <p>
     * 根据id获取实体
     * </p>
     *
     * @param id 主键ID
     * @return 主键对应的实体，没有返回 null
     */
    T getById(Serializable id);

    /**
     * <p>
     * 根据条件获取一个实体
     * </p>
     *
     * @param queryWrapper 实体对象查询操作
     * @return 符合条件的一个实体，没有返回 null
     */
    T getOne(Wrapper<T> queryWrapper);

    /**
     * <p>
     * 根据条件，查询总数
     * </p>
     *
     * @param queryWrapper 实体对象查询操作
     * @return 符合条件的实体数量
     */
    int count(Wrapper<T> queryWrapper);

    /**
     * <p>
     * 根据条件查询实体集合
     * </p>
     *
     * @param queryWrapper 实体对象查询操作
     * @return 符合条件的实体 List 集合，没有返回没有元素的空 List 集合
     */
    List<T> list(Wrapper<T> queryWrapper);

    /**
     * <p>
     * 根据条件和分页进行分页查询
     * </p>
     *
     * @param page         分页对象
     * @param queryWrapper 实体对象查询操作
     * @return 符合条件的分页查询结果
     */
    IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);
}
