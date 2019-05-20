package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.cove.application.TravellerApplication;
import com.carbybus.cove.domain.view.TravellerLoginInput;
import com.carbybus.cove.domain.view.TravellerSignupInput;
import com.carbybus.infrastructure.component.ActionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 凭证控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"用户"})
@Validated
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController extends BaseController {
    private TravellerApplication travellerApp;

    @Autowired
    public UserController(TravellerApplication travellerApp) {
        this.travellerApp = travellerApp;
    }

    @ApiOperation(value = "登录", notes = "登录并获取TOKEN")
    @GetMapping(value = "login")
    public ActionResult login(@Valid TravellerLoginInput input) {
        return travellerApp.login(input);
    }

    @ApiOperation(value = "注册", notes = "注册")
    @GetMapping(value = "signup")
    public ActionResult signup(@Valid TravellerSignupInput input) {
        return travellerApp.signup(input);
    }
}