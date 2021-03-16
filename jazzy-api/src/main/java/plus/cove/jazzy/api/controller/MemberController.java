package plus.cove.jazzy.api.controller;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.cove.jazzy.api.component.BaseController;

/**
 * 会员控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"会员"})
@Validated
@RestController
@RequestMapping(value = "/member")
public class MemberController extends BaseController {
}
