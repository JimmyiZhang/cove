package com.carbybus.cove.domain.entity.company;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 员工
 * 主要包括管理员和车队长
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "employee")
public class Employee extends DefaultEntity {
    /**
     * 用户名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 公司编码
     */
    private long companyId;

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    public static Employee create(long companyId) {
        Employee employee = new Employee();
        employee.valueOf();

        employee.setCompanyId(companyId);
        return employee;
    }
}