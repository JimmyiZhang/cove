package plus.cove.infrastructure.component;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
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
     * 插入实体对象
     *
     * @param entity 插入的实体
     * @return 受影响的行数
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    int insert(T entity);

    /**
     * 条件删除
     *
     * @param wrapper 删除条件
     * @return 受影响的行数
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    int delete(@Param("ew") Wrapper<T> wrapper);

    /**
     * 根据主键删除实体
     *
     * @param id 主键
     * @return 受影响的行数
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    int deleteById(Serializable id);

    /**
     * 根据id集合批量删除
     *
     * @param idList 要删除的主键集合
     * @return 受影响的行数
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    int deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList);

    /**
     * 条件更新实体
     *
     * @param entity        更新的实体
     * @param updateWrapper 更新的条件
     * @return 受影响的行数
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    int update(@Param("et") T entity, @Param("ew") Wrapper<T> updateWrapper);

    /**
     * <p>
     * 根据id更新实体
     * </p>
     * null值不更新
     *
     * @param entity 实体
     * @return 受影响的行数
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    int updateById(@Param("et") T entity);

    /**
     * 根据id获取实体
     *
     * @param id 主键
     * @return 主键对应的实体
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    T selectById(Serializable id);

    /**
     * 获取条件一个实体
     *
     * @param queryWrapper 查询条件
     * @return 符合条件的一个实体，没有符合条件的返回 null
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    T selectOne(@Param("ew") Wrapper<T> queryWrapper);

    /**
     * 根据条件获取实体列表
     *
     * @param queryWrapper 查询条件
     * @return 符合条件的实体 List，没有符合条件的返回没有元素的空 List
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    List<T> selectList(@Param("ew") Wrapper<T> queryWrapper);

    /**
     * 根据id集合获取实体列表
     *
     * @param idList 主键集合
     * @return 符合条件的实体 List，没有符合条件的返回没有元素的空 List
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    List<T> selectBatchIds(@Param("coll") Collection<? extends Serializable> idList);

    /**
     * 根据条件和分页获取分页实体
     *
     * @param page         分页参数
     * @param queryWrapper 查询条件
     * @return 符合条件的分页实体
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    IPage<T> selectPage(IPage<T> page, @Param("ew") Wrapper<T> queryWrapper);

    /**
     * 查询条数
     *
     * @param queryWrapper 查询条件
     * @return 符合条件的实体数量
     * @author jimmy.zhang
     * @date 2019-03-27
     */
    Integer selectCount(@Param("ew") Wrapper<T> queryWrapper);
}
