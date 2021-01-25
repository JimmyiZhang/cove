package plus.cove.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import plus.cove.infrastructure.mybatis.provider.SelectRepositoryProvider;
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
public interface SelectByIdMapper<T> {
    @SelectProvider(
            type = SelectRepositoryProvider.class,
            method = "dynamicSQL"
    )
    T selectById(Object id);
}
