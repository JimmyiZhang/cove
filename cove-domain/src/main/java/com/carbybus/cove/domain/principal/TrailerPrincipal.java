package com.carbybus.cove.domain.principal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 车辆凭证
 * 或者车辆信息使用
 * 通过缓存保存，以deviceNo作为key值
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
public class TrailerPrincipal {

    /**
     * 设备号
     */
    private String deviceNo;

    /**
     * 车辆id
     */
    private long trailerId;

    /**
     * 车队id
     */
    private long fleetId;

    /**
     * 公司id
     */
    private long companyId;

    /**
     * 总车位数
     */
    private int totalPlace;

    /**
     * 重载位数
     */
    private int heavyPlace;

    /**
     * 当前运输台数
     */
    private int carriage;

}