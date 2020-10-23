package plus.cove.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.UpdateProvider;
import plus.cove.infrastructure.mybatis.provider.UpdateRepositoryProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 基础仓储Mapper
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
@RegisterMapper
public interface UpdateByIdMapper<T> {
    @UpdateProvider(
            type = UpdateRepositoryProvider.class,
            method = "dynamicSQL"
    )
    int updateById(T entity);
}
