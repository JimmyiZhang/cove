package plus.cove.infrastructure.mybatis;

import plus.cove.infrastructure.mybatis.mapper.*;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.base.delete.DeleteMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectCountMapper;
import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;

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
@RegisterMapper
public interface MybatisRepository<T> extends
        SelectMapper<T>, SelectOneMapper<T>, SelectAllMapper<T>, SelectCountMapper<T>, SelectByIdMapper<T>,
        InsertMapper<T>, InsertAllMapper<T>,
        UpdateByIdMapper<T>, UpdateAllByIdMapper<T>,
        DeleteMapper<T>, DeleteByIdMapper<T> {
}
