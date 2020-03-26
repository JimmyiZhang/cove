package plus.cove.infrastructure.component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 转换
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-13
     */
    public IPage toPage() {
        IPage page = new Page(this.page, this.size);
        return page;
    }
}
