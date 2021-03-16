package plus.cove.infrastructure.component;

import java.util.List;

/**
 * page帮助类
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
public interface PageHelper {
    /**
     * 从PageModel转换
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    <E> E fromModel(PageModel model);

    /**
     * 转换为PageResult
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    <F> PageResult<F> toResult(List<F> rows);
}
