package plus.cove.infrastructure.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.provider.base.BaseDeleteProvider;

/**
 * 基础仓储方法
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
public class DeleteRepositoryProvider extends BaseDeleteProvider {
    public DeleteRepositoryProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 根据id删除
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public String deleteById(MappedStatement ms) {
        return deleteByPrimaryKey(ms);
    }
}
