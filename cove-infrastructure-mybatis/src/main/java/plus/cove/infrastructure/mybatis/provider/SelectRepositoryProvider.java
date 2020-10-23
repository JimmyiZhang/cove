package plus.cove.infrastructure.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.provider.base.BaseSelectProvider;

/**
 * 基础仓储方法
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
public class SelectRepositoryProvider extends BaseSelectProvider {
    public SelectRepositoryProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 根据id获取
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public String selectById(MappedStatement ms) {
        return selectByPrimaryKey(ms);
    }
}
