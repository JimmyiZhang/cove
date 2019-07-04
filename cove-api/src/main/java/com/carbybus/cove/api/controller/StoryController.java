package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.cove.application.StoryApplication;
import com.carbybus.cove.domain.entity.coordinate.CoordinateAround;
import com.carbybus.cove.domain.entity.journey.Story;
import com.carbybus.cove.domain.exception.StoryError;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.domain.view.StoryViewOutput;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.file.*;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"故事"})
@RestController
@RequestMapping(value = "/story")
public class StoryController extends BaseController {
    @Autowired
    private UniteUploadConfig uploadConfig;

    @Autowired
    private StoryApplication storyApp;

    @RequestMapping(value = "near", method = RequestMethod.GET)
    public ActionResult near(CoordinateAround input) {
        List<StoryViewOutput> output = storyApp.listByNear(input);
        return success(output);
    }
}
