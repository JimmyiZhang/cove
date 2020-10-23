package plus.cove.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import plus.cove.infrastructure.mybatis.provider.InsertRepositoryProvider;
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
public interface InsertAllMapper<T> {
    @InsertProvider(
            type = InsertRepositoryProvider.class,
            method = "dynamicSQL"
    )
    int insertAll(T entity);
}
