package com.carbybus.cove.domain.entity.trailer;

import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 车辆实体基类
 * </p>
 * 包括创建时间，车队编码，公司编码
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Data
public class TrailerEntity extends DefaultEntity {
    /**
     * 车辆编号
     */
    private long trailerId;

    /**
     * 车队编号
     */
    private long fleetId;

    /**
     * 公司编号
     */
    private long companyId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
