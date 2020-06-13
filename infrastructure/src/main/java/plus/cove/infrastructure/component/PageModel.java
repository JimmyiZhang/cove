package plus.cove.infrastructure.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页信息
 *
 * @author jimmy.zhang
 * @date 2019-08-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageModel {
    public static final PageModel DEFAULT = new PageModel(1, 10);

    /**
     * 当前页码
     */
    private int page;

    /**
     * 页大小
     */
    private int size;
}
