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
    public final static PageModel DEFAULT = PageModel.of(1, 10);

    /**
     * 当前页码
     */
    private int page;

    /**
     * 页大小
     */
    private int size;

    private PageModel() {
    }

    public static PageModel of(Integer page, Integer size) {
        PageModel model = new PageModel();
        model.page = (page == null || page.intValue() < 1 ? 1 : page);
        model.size = (size == null || size.intValue() < 1 ? 1 : size);
        return model;
    }
}
