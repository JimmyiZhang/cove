package plus.cove.jazzy.domain.entity.limiting;

import lombok.Data;
import plus.cove.infrastructure.utils.AssertHelper;

import java.time.LocalDateTime;

/**
 * 限流条件
 * <p>
 * 对象在相应维度的汇总以及限制
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Data
public class LimitingCondition {
    /**
     * 目标
     */
    private String target;
    /**
     * 类别
     */
    private String category;
    /**
     * 维度
     * 按小时按天还是按月
     */
    private LimitingDimension dimension;
    /**
     * 限制
     * <p>
     * 上限
     */
    private Integer limitation;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 根据结束时间计算开始时间
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    private void calculateTime(LimitingDimension dimension, LocalDateTime endTime) {
        AssertHelper.assertNotNull(endTime);
        switch (dimension) {
            case MINUTE -> this.startTime = endTime.minusMinutes(1);
            case HOUR -> this.startTime = endTime.minusHours(1);
            case DAY -> this.startTime = endTime.minusDays(1);
            case WEEK -> this.startTime = endTime.minusDays(7);
        }
    }

    /**
     * 私有构造函数
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    private LimitingCondition() {
    }

    public static LimitingCondition createWithHour(String target, String category, Integer limit) {
        return create(target, category, LimitingDimension.HOUR, limit);
    }

    public static LimitingCondition createWithDay(String target, String category, Integer limit) {
        return create(target, category, LimitingDimension.DAY, limit);
    }

    public static LimitingCondition create(
            String target,
            String category,
            LimitingDimension dimension,
            Integer limit) {
        LimitingCondition config = new LimitingCondition();
        config.target = target;
        config.category = category;
        config.dimension = dimension;
        config.limitation = limit;
        config.calculateTime(dimension, LocalDateTime.now());
        return config;
    }
}
