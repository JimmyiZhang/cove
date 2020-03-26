package plus.cove.jazzy.api.controller;

import plus.cove.infrastructure.component.ActionResult;
import plus.cove.jazzy.api.component.BaseController;
import plus.cove.jazzy.application.DiscoveryApplication;
import plus.cove.jazzy.domain.entity.discovery.DiscoveryPlace;
import plus.cove.jazzy.domain.view.DiscoveryInput;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 探索控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"探索游戏"})
@Validated
@RestController
@RequestMapping(value = "/discovery")
public class DiscoveryController extends BaseController {
    @Autowired
    private DiscoveryApplication discoveryApp;

    @RequestMapping(value = "place", method = RequestMethod.GET)
    public ActionResult getPlace(@Valid DiscoveryInput input) {
        DiscoveryPlace output = discoveryApp.generatePlace(input);
        return success(output);
    }
}
