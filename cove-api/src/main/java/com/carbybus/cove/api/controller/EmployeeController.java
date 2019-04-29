package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.cove.domain.entity.company.Employee;
import com.carbybus.infrastructure.component.ActionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 员工控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"员工"})
@Validated
@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EmployeeController extends BaseController {
    @ApiOperation(value = "获取某个员工", notes = "获取员工详细信息")
    @GetMapping(value = "/one")
    public ActionResult getOne() {
        Employee employee = Employee.create(1)
                .setName("jimmy")
                .setPhone("13911006493");

        return success(employee);
    }

    @ApiOperation(value = "保存某个员工", notes = "保存员工信息")
    @PostMapping(value = "/one")
    public ActionResult postOne(@RequestBody Employee employee) {
        return success(employee);
    }

}
