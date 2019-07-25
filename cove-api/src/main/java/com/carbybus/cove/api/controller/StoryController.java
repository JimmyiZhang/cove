package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.cove.application.StoryApplication;
import com.carbybus.cove.domain.entity.coordinate.CoordinateAround;
import com.carbybus.cove.domain.view.StoryViewOutput;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.file.UniteUploadConfig;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private UniteUploadConfig uploadConfig;

    @Autowired
    private StoryApplication storyApp;

    @RequestMapping(value = "near", method = RequestMethod.GET)
    public ActionResult near(@Valid CoordinateAround input) {
        List<StoryViewOutput> output = storyApp.listByNear(input);
        return success(output);
    }
}
