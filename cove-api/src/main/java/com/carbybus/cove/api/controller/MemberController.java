package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.cove.application.StoryApplication;
import com.carbybus.cove.domain.entity.coordinate.CoordinateAround;
import com.carbybus.cove.domain.entity.journey.Story;
import com.carbybus.cove.domain.exception.StoryError;
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
 * 会员控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"会员"})
@RestController
@RequestMapping(value = "/member")
public class MemberController extends BaseController {
    @Autowired
    private UniteUploadConfig uploadConfig;

    @Autowired
    private StoryApplication storyApp;

    @RequestMapping(value = "upload", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ActionResult upload(@RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam(value = "name", required = false) String name) throws IOException {
        // 上传文件
        String fileName = StringUtils.isEmpty(name) ? file.getOriginalFilename() : name;
        UploadFileResult result = UploadFileUtils.saveFile(uploadConfig.getUploadPath(), fileName, file.getInputStream());

        // 保存故事
        FileMetadata metadata = FileUtils.getFileMetadata(result);
        if (!metadata.isValid()) {
            return failure(StoryError.INVALID_COORDINATE);
        }

        Story story = Story.fromFile(this.getUser(), metadata);
        storyApp.save(story);

        result.setSize(file.getSize());
        result.setType(file.getContentType());
        return success(result);
    }
}
