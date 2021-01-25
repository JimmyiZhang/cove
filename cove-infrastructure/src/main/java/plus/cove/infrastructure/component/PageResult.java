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
    private Integer page;

    /**
     * 页大小
     */
    private Integer size;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 总记录数
     */
    private Long total;

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
    public static <E> PageResult<E> from(List<E> rows, Integer page, Integer size, Long total) {
        PageResult<E> result = new PageResult();
        result.page = page;
        result.size = size;
        result.total = total;
        result.rows = rows;

        if (total != null && size != null) {
            int more = (int) (total % size);
            int pages = (int) (total / size);

            result.pages = pages + (more == 0 ? 0 : 1);
        }
        return result;
    }
}
