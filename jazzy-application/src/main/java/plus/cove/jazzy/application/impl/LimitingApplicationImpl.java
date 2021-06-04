package plus.cove.jazzy.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.cove.infrastructure.utils.AssertHelper;
import plus.cove.jazzy.application.LimitingApplication;
import plus.cove.jazzy.domain.entity.limiting.LimitingCondition;
import plus.cove.jazzy.domain.entity.limiting.LimitingTarget;
import plus.cove.jazzy.repository.LimitingRepository;

import java.time.LocalDateTime;


/**
 * 限流应用
 *
 * @author jimmy.zhang
 * @date 2021-06-02
 */
@Service
public class LimitingApplicationImpl implements LimitingApplication {
    @Autowired
    LimitingRepository limitingRep;

    @Override
    public LimitingTarget loadTarget(LimitingCondition condition) {
        AssertHelper.assertNotNull(condition);
        Integer totalValue = limitingRep.selectTarget(condition);

        LimitingTarget target = new LimitingTarget();
        target.setTotalValue(totalValue);

        target.setCategory(condition.getCategory());
        target.setTarget(condition.getTarget());
        target.setTargetTime(LocalDateTime.now());
        target.setLimitValue(condition.getLimitation());

        return target;
    }

    @Override
    public void saveTarget(LimitingTarget target) {
        limitingRep.saveTarget(target);
    }
}
