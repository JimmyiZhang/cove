package com.carbybus.cove.domain.entity.trailer;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.coordinate.Coordinate;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 车辆信息
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "trailer")
public class Trailer extends DefaultEntity {
    /**
     * 车牌号
     */
    private String plateNo;

    /**
     * 运输状态
     */
    private TrailerRunState runState;

    /**
     * 总空位数
     */
    private int totalPlace;

    /**
     * 已用位数
     */
    private int usedPlace;

    /**
     * 坐标
     * 国内坐标
     */
    private Coordinate coordinate;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 详细位置
     */
    private String location;

    /**
     * 速度
     */
    private int speed;

    /**
     * 油量
     */
    private int fuel;

    /**
     * 载重
     */
    private int load;

    /**
     * 现载量
     */
    private int carriage;

    /**
     * 总油量？
     */
    private int totalFuel;

    /**
     * 总里程？
     */
    private int totalMileage;

    /**
     * 油箱底面积
     */
    private int tankArea;

    /**
     * 最后更新日期
     */
    private LocalDateTime updateTime;

    /**
     * 司机名称
     */
    private String driverName;

    /**
     * 司机电话
     */
    private String driverPhone;

    /**
     * 车队编码
     */
    private long fleetId;

    /**
     * 公司编码
     */
    private long companyId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}