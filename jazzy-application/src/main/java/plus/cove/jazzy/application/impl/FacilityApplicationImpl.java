package plus.cove.jazzy.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.cove.infrastructure.utils.AssertHelper;
import plus.cove.jazzy.application.FacilityApplication;
import plus.cove.jazzy.domain.facility.*;
import plus.cove.jazzy.repository.FacilityLimitingRepository;
import plus.cove.jazzy.repository.FacilityVersioningRepository;

import java.time.LocalDateTime;


/**
 * 限流应用
 *
 * @author jimmy.zhang
 * @date 2021-06-02
 */
@Service
public class FacilityApplicationImpl implements FacilityApplication {
    @Autowired
    FacilityLimitingRepository limitingRep;
    @Autowired
    FacilityVersioningRepository versioningRep;

    @Override
    public LimitingTarget loadLimitingTarget(LimitingCondition condition) {
        AssertHelper.assertNotNull(condition);
        Integer totalValue = limitingRep.selectLimiting(condition);

        LimitingTarget target = new LimitingTarget();
        target.setTotalValue(totalValue);

        target.setCategory(condition.getCategory());
        target.setTarget(condition.getTarget());
        target.setTargetTime(LocalDateTime.now());
        target.setLimitValue(condition.getLimitation());

        return target;
    }

    @Override
    public void saveLimitingTarget(LimitingTarget target) {
        limitingRep.saveLimiting(target);
    }

    @Override
    public VersioningTarget loadVersioningTarget(VersioningCondition condition) {
        Versioning entity = versioningRep.selectVersioning(condition);
        versioningRep.updateVersioning(entity);

        VersioningTarget target = VersioningTarget.from(entity);
        return target;
    }

    @Override
    public void saveVersioningTarget(VersioningTarget target) {
        Versioning entity = Versioning.create(target.getCode(), target.getRandom());
        versioningRep.insertVersioning(entity);
    }
}
