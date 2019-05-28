package com.carbybus.cove.application.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 分页帮助类
 *
 * @author jimmy.zhang
 * @date 2019-05-28
 */
public class PageUtils {
    private PageUtils() {
    }

    public static IPage defaultPage() {
        return new Page(1L, 100L);
    }

    public static List defaultList(IPage page, Function<IPage, List> func) {
        if (page.getTotal() < 1) {
            return new ArrayList();
        }

        return func.apply(page);
    }
}
