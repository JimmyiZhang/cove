package plus.cove.jazzy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.jazzy.api.component.BaseController;
import plus.cove.jazzy.application.DistrictApplication;
import plus.cove.jazzy.domain.entity.common.DistrictCity;

import java.util.List;

/**
 * 区域控制器
 *
 * @author jimmy.zhang
 * @since 1.1
 */
@Api(tags = {"区域"})
@Validated
@RestController
@RequestMapping(value = "/district")
public class DistrictController extends BaseController {
    private DistrictApplication cityApp;

    @Autowired
    public DistrictController(DistrictApplication cityApp) {
        this.cityApp = cityApp;
    }

    @ApiOperation(value = "获取所有城市", notes = "获取所有城市")
    @GetMapping(value = "city")
    public ActionResult allCity() {
        List<DistrictCity> list = cityApp.findAllCity();
        return success(list);
    }
}
