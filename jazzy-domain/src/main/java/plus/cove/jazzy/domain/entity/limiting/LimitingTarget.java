package plus.cove.jazzy.domain.entity.limiting;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 限流对象
 * <p>
 * 对象在相应维度的汇总以及限制
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Data
public class LimitingTarget {
    /**
     * 配置类别
     */
    private String category;
    /**
     * 汇总值
     */
    private Integer totalValue;
    /**
     * 限制值
     */
    private Integer limitValue;

    /**
     * 执行目标
     */
    private String target;
    /**
     * 执行时间
     */
    private LocalDateTime targetTime;

    /**
     * 验证是否超过限流
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public boolean exceedLimit() {
        if (this.limitValue == null || this.totalValue == null) {
            return true;
        }
        return totalValue.intValue() >= limitValue.intValue();
    }

    public static LimitingTarget fromCondition(LimitingCondition condition) {
        LimitingTarget target = new LimitingTarget();
        target.setCategory(condition.getCategory());
        target.setLimitValue(condition.getLimitation());
        return target;
    }
}
