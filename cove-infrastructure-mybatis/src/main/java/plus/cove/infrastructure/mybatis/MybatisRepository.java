package plus.cove.infrastructure.mybatis;

import io.mybatis.mapper.base.EntityProvider;
import io.mybatis.mapper.example.Example;
import io.mybatis.mapper.example.ExampleProvider;
import io.mybatis.mapper.list.ListProvider;
import io.mybatis.provider.Caching;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
public interface MybatisRepository<T, K extends Serializable> {
    @Lang(Caching.class)
    @InsertProvider(type = EntityProvider.class, method = "insertSelective")
    int insert(T entity);

    @Lang(Caching.class)
    @InsertProvider(type = EntityProvider.class, method = "insert")
    int insertAll(T entity);

    @Lang(Caching.class)
    @InsertProvider(type = ListProvider.class, method = "insertList")
    int insertList(@Param("entityList") List<? extends T> entityList);

    @Lang(Caching.class)
    @DeleteProvider(type = EntityProvider.class, method = "delete")
    int delete(T entity);

    @Lang(Caching.class)
    @DeleteProvider(type = EntityProvider.class, method = "deleteByPrimaryKey")
    int deleteById(K id);

    @Lang(Caching.class)
    @DeleteProvider(type = ExampleProvider.class, method = "deleteByExample")
    int deleteByExample(Example<T> example);

    @Lang(Caching.class)
    @UpdateProvider(type = EntityProvider.class, method = "updateByPrimaryKeySelective")
    int updateById(T entity);

    @Lang(Caching.class)
    @UpdateProvider(type = ExampleProvider.class, method = "updateByExampleSelective")
    int updateByExample(@Param("entity") T entity, @Param("example") Example<T> example);

    @Lang(Caching.class)
    @UpdateProvider(type = EntityProvider.class, method = "updateByPrimaryKey")
    int updateAllById(T entity);

    @Lang(Caching.class)
    @UpdateProvider(type = ExampleProvider.class, method = "updateByExample")
    int updateAllByExample(@Param("entity") T entity, @Param("example") Example<T> example);

    @Lang(Caching.class)
    @SelectProvider(type = EntityProvider.class, method = "selectByPrimaryKey")
    Optional<T> selectById(K id);

    @Lang(Caching.class)
    @SelectProvider(type = EntityProvider.class, method = "select")
    List<T> selectList(T entity);

    @Lang(Caching.class)
    @SelectProvider(type = ExampleProvider.class, method = "selectByExample")
    List<T> selectListByExample(Example<T> example);

    @Lang(Caching.class)
    @SelectProvider(type = EntityProvider.class, method = "selectCount")
    long selectCount(T entity);

    @Lang(Caching.class)
    @SelectProvider(type = ExampleProvider.class, method = "countByExample")
    long selectCountByExample(Example<T> example);
}
