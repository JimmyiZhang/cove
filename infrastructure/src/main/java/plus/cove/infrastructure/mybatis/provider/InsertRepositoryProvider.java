package plus.cove.infrastructure.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

/**
 * 基础仓储方法
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
public class InsertRepositoryProvider extends BaseInsertProvider {
    public InsertRepositoryProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 插入数据
     *
     * @param ms
     * @return
     */
    public String insertAll(MappedStatement ms) {
        return insertSelective(ms);
    }
}
