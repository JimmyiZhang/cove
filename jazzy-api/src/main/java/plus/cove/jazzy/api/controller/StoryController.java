package plus.cove.jazzy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.jazzy.api.component.BaseController;
import plus.cove.jazzy.application.StoryApplication;
import plus.cove.jazzy.domain.entity.coordinate.CoordinateAround;
import plus.cove.jazzy.domain.view.StoryViewOutput;

import javax.validation.Valid;
import java.util.List;

/**
 * 故事控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"故事"})
@Validated
@RestController
@RequestMapping(value = "/story")
public class StoryController extends BaseController {
    @Autowired
    StoryApplication storyApp;

    @ApiOperation(value = "附近的世界", notes = "根据坐标获取附近的故事")
    @RequestMapping(value = "near", method = RequestMethod.GET)
    public ActionResult near(@Valid CoordinateAround input) {
        List<StoryViewOutput> output = storyApp.listByNear(input);
        return success(output);
    }
}
