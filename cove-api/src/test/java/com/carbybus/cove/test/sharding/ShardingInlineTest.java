package com.carbybus.cove.test.sharding;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.domain.entity.company.Employee;
import com.carbybus.cove.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

// 内置分片测试
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class ShardingInlineTest {
    @Autowired
    private EmployeeRepository employeeRep;

    @Test
    public void tableInlineWriteStrategy1() {
        Employee employee = Employee.create(1);
        employee.setName("employee_1").setPhone("13911006493");

        System.out.println(employee.getCompanyId());

        // master, employee_1
        employeeRep.insert(employee);
        Assert.notNull(employee, "员工保存");
    }

    @Test
    public void tableInlineWriteStrategy2() {
        Employee employee = Employee.create(2);
        employee.setName("employee_0").setPhone("13911006493");

        System.out.println(employee.getCompanyId());

        // master, employee_0
        employeeRep.insert(employee);
        Assert.notNull(employee, "员工保存");
    }

    @Test
    public void tableInlineReadStrategy() {
        Wrapper<Employee> condition = new LambdaQueryWrapper<Employee>()
                .eq(Employee::getCompanyId, 1);

        List<Employee> employees = employeeRep.selectList(condition);
        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }

        Assert.notNull(employees, "员工读取");
    }
}
