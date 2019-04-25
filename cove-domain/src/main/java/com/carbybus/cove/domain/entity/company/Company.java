package com.carbybus.cove.domain.entity.company;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司信息
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "company")
public class Company extends DefaultEntity {
    /**
     * 名称
     */
    private String name;

    /**
     * 预估收入系数
     */
    private int incomeFee;

    /**
     * 路桥费
     */
    private int tollFee;

    /**
     * 燃油费
     */
    private int fuelFee;

    /**
     * 司机费用
     */
    private int driverFee;

    /**
     * 预警时速
     * 单位:km/h
     */
    private int speedWarning;

    /**
     * 载重设置
     */
    @TableField(exist = false)
    private List<CompanyTrailerLoading> loadings;

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    public static Company create() {
        Company company = new Company();
        company.valueOf();

        List<CompanyTrailerLoading> loadings = new ArrayList<>();
        company.setSpeedWarning(80)
                .setLoadings(loadings);
        return company;
    }
}