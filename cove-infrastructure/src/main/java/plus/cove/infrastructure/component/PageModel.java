package plus.cove.infrastructure.component;

import lombok.Data;

/**
 * 分页信息
 *
 * @author jimmy.zhang
 * @date 2019-08-13
 */
@Data
public class PageModel {
    public final static PageModel DEFAULT = new PageModel(1, 10);

    /**
     * 当前页码
     */
    private int page;

    /**
     * 页大小
     */
    private int size;

    public PageModel() {
    }

    public PageModel(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
