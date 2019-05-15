package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.cove.application.AccountApplication;
import com.carbybus.cove.domain.view.UserLoginInput;
import com.carbybus.cove.domain.view.UserRefreshInput;
import com.carbybus.infrastructure.component.ActionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 凭证控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"凭证"})
@Validated
@RestController
@RequestMapping(value = "/tokens", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TokenController extends BaseController {
    private AccountApplication accountApp;

    @Autowired
    public TokenController(AccountApplication accountApp) {
        this.accountApp = accountApp;
    }

    @ApiOperation(value = "获取TOKEN", notes = "生成TOKEN")
    @GetMapping(value = "access")
    public ActionResult accessToken(@Valid UserLoginInput input) {
        return accountApp.accessToken(input);
    }

    @ApiOperation(value = "刷新TOKEN", notes = "刷新TOKEN")
    @GetMapping(value = "refresh")
    public ActionResult refreshToken(@Valid UserRefreshInput input) {
        return accountApp.refreshToken(input);
    }
}
