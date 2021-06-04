package plus.cove.jazzy.application;


import plus.cove.jazzy.domain.entity.limiting.LimitingCondition;
import plus.cove.jazzy.domain.entity.limiting.LimitingTarget;

/**
 * 限流应用
 *
 * @author jimmy.zhang
 * @date 2021-06-02
 */
public interface LimitingApplication {
    /**
     * 获取限流对象
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    LimitingTarget loadTarget(LimitingCondition condition);

    /**
     * 保存限流对象
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    void saveTarget(LimitingTarget target);
}
