package plus.cove.infrastructure.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 分页信息
 *
 * @author jimmy.zhang
 * @date 2019-08-13
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
     * 总记录数
     */
    private int total;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 数据
     */
    private List<E> rows;
}
