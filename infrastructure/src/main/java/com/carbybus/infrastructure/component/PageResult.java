package com.carbybus.infrastructure.component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页信息
 *
 * @author jimmy.zhang
 * @date 2019-08-13
 */
@Data
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

    /**
     * 转换
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-13
     */
    public static PageResult from(IPage page) {
        PageResult result = new PageResult();
        result.setPage((int) page.getCurrent());
        result.setSize((int) page.getSize());
        result.setPages((int) page.getPages());
        result.setTotal((int) page.getTotal());
        result.setRows(page.getRecords());

        return result;
    }
}
