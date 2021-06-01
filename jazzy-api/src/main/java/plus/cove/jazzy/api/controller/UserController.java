package plus.cove.jazzy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.jazzy.api.component.BaseController;
import plus.cove.jazzy.application.AuthorApplication;
import plus.cove.jazzy.domain.principal.UserRequest;
import plus.cove.jazzy.domain.view.UserActiveInput;
import plus.cove.jazzy.domain.view.UserLoginInput;
import plus.cove.jazzy.domain.view.UserSignupInput;

import javax.validation.Valid;

/**
 * 用户控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"用户"})
@Validated
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    private AuthorApplication travellerApp;

    @Autowired
    public UserController(AuthorApplication travellerApp) {
        this.travellerApp = travellerApp;
    }

    @ApiOperation(value = "登录", notes = "用户登录并获取TOKEN")
    @PostMapping(value = "login")
    public ActionResult login(@RequestBody @Valid UserLoginInput input) {
        UserRequest request = this.getUserRequest();
        input.withRequest(request);

        return travellerApp.login(input);
    }

    @ApiOperation(value = "注册", notes = "用户注册")
    @PostMapping(value = "signup")
    public ActionResult signup(@RequestBody @Valid UserSignupInput input) {
        UserRequest request = this.getUserRequest();
        input.withRequest(request);

        return travellerApp.signup(input);
    }

    @ApiOperation(value = "激活", notes = "用户激活")
    @GetMapping(value = "active")
    public ActionResult active(@Valid UserActiveInput input) {
        return travellerApp.active(input);
    }
}
