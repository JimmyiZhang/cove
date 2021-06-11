package plus.cove.jazzy.application;


import plus.cove.jazzy.domain.facility.*;

/**
 * 特殊功能应用
 *
 * 包括限流，去重
 *
 * @author jimmy.zhang
 * @date 2021-06-02
 */
public interface FacilityApplication {
    /**
     * 获取限流对象
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    LimitingTarget loadLimitingTarget(LimitingCondition condition);

    /**
     * 保存限流对象
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    void saveLimitingTarget(LimitingTarget target);

    /**
     * 获取版本对象
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    VersioningTarget loadVersioningTarget(VersioningCondition condition);

    /**
     * 保存版本
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    void saveVersioningTarget(VersioningTarget target);
}
