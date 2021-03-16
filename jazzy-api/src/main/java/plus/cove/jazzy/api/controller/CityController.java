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
import plus.cove.jazzy.application.CityApplication;
import plus.cove.jazzy.domain.entity.city.City;

import java.util.List;

/**
 * 城市控制器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Api(tags = {"城市"})
@Validated
@RestController
@RequestMapping(value = "/city")
public class CityController extends BaseController {
    private CityApplication cityApp;

    @Autowired
    public CityController(CityApplication cityApp) {
        this.cityApp = cityApp;
    }

    @ApiOperation(value = "获取所有城市", notes = "获取所有城市")
    @GetMapping(value = "all")
    public ActionResult login() {
        List<City> list = cityApp.findAllCity();
        return success(list);
    }
}
