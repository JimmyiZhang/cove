package plus.cove.infrastructure.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * 函数帮助类
 *
 * @author jimmy.zhang
 * @since 2.0
 */
public class FunctionHelper {
    /**
     * 结合
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static <T, E> void combine(List<T> origin, List<E> target, BiConsumer<T, E> consumer) {
        if (CollUtil.isEmpty(origin) || CollUtil.isEmpty(target)) {
            return;
        }

        // 双循环
        for (T ori : origin) {
            for (E tar : target) {
                consumer.accept(ori, tar);
            }
        }
    }
}
