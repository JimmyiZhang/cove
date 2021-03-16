package plus.cove.infrastructure.converter;

/**
 * 基础转换接口
 *
 * @author jimmy.zhang
 * @date 2019-04-04
 */
public interface BaseConverter {
    /**
     * 从源数据转换为指定类型的数据
     *
     * @param origin      来源数据
     * @param targetClass 目标数据类型
     * @return 目标数据
     * @author jimmy.zhang
     * @date 2019-04-04
     */
    <O, T> T convert(O origin, Class<T> targetClass);

    /**
     * 从来源数据更新为目标数据
     *
     * @param origin 来源数据
     * @param target 目标数据
     * @author jimmy.zhang
     * @date 2019-04-04
     */
    <O, T> void convert(O origin, T target);
}
