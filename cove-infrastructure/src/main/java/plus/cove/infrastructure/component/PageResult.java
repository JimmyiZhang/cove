package plus.cove.infrastructure.component;

import lombok.Data;

import java.util.List;

/**
 * 分页信息
 *
 * @author jimmy.zhang
 * @date 2019-08-13
 */
@Data
public class PageResult<E> {
    /**
     * 当前页码
     */
    private int page;

    /**
     * 页大小
     */
    private int size;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 数据
     */
    private List<E> rows;

    /**
     * 从PageModel和Rows组装PageResult
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static <E> PageResult<E> from(int page, int size, long total, List<E> rows) {
        PageResult<E> result = new PageResult();
        result.page = page;
        result.size = size;
        result.total = total;
        result.rows = rows;

        if (size > 0) {
            int more = (int) (total % size);
            int pages = (int) (total / size);
            result.pages = pages + (more == 0 ? 0 : 1);
        } else {
            result.pages = 1;
        }
        return result;
    }

    public static <E> PageResult<E> from(PageModel page, Long total, List<E> rows) {
        return from(page.getPage(), page.getSize(), total, rows);
    }
}
