package plus.cove.infrastructure.mybatis.helper;

import com.github.pagehelper.PageRowBounds;
import plus.cove.infrastructure.component.PageHelper;
import plus.cove.infrastructure.component.PageModel;
import plus.cove.infrastructure.component.PageResult;

import java.util.List;

/**
 * page帮助类
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
public class MybatisPageHelper implements PageHelper {
    private PageRowBounds pageBounds;

    /**
     * 从PageModel转换
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    @Override
    public PageRowBounds fromModel(PageModel model) {
        pageBounds = new PageRowBounds(model.getPage(), model.getSize());
        return pageBounds;
    }

    /**
     * 转换为PageResult
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    @Override
    public <E> PageResult<E> toResult(List<E> rows) {
        PageResult<E> result = PageResult.from(rows,
                pageBounds.getOffset(),
                pageBounds.getLimit(),
                pageBounds.getTotal());
        return result;
    }
}
