package plus.cove.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import plus.cove.infrastructure.mybatis.provider.DeleteRepositoryProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 基础仓储Mapper
 *
 * @param
 * @return
 * @author jimmy.zhang
 * @since 1.0
 */
@RegisterMapper
public interface DeleteByIdMapper<T> {
    @DeleteProvider(
            type = DeleteRepositoryProvider.class,
            method = "dynamicSQL"
    )
    int deleteById(Object id);
}
