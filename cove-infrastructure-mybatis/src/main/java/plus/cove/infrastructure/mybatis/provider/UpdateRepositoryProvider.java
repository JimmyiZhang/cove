package plus.cove.infrastructure.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.provider.base.BaseUpdateProvider;

/**
 * 基础仓储方法
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
public class UpdateRepositoryProvider extends BaseUpdateProvider {
    public UpdateRepositoryProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 根据id更新
     * null不更新
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public String updateById(MappedStatement ms) {
        return updateByPrimaryKey(ms);
    }

    /**
     * 根据id更新
     * null更新
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public String updateAllById(MappedStatement ms) {
        return updateByPrimaryKeySelective(ms);
    }
}
