package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.cove.application.AccountApplication;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"账户"})
@Validated
@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController extends BaseController {
    private AccountApplication accountApp;

    @Autowired
    public AccountController(AccountApplication accountApp) {
        this.accountApp = accountApp;
    }
}
