package com.carbybus.infrastructure.sharding;

import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MasterOnlyShardingAspet {
    private final Logger logger = LoggerFactory.getLogger(MasterOnlyShardingAspet.class);

    /**
     * 启用 Hint管理
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-04-25
     */
    @Around("@annotation(com.carbybus.infrastructure.sharding.MasterOnlySharding)")
    public void startHintManager(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("start MasterRouteOnly of sharding-jdbc");
        try (HintManager hintManager = HintManager.getInstance()) {
            hintManager.setMasterRouteOnly();
            joinPoint.proceed();
            logger.debug("end MasterRouteOnly of sharding-jdbc");
        }
    }
}
