package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.cove.application.AccountApplication;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.infrastructure.component.ActionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"管理"})
@Validated
@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminController extends BaseController {
    private AccountApplication accountApp;

    @Autowired
    public AdminController(AccountApplication accountApp) {
        this.accountApp = accountApp;
    }

    @ApiOperation(value = "当前用户", notes = "获取用户详细信息")
    @GetMapping(value = "/me")
    public ActionResult me() {
        UserPrincipal user = this.getUser();
        return success(user);
    }
}
