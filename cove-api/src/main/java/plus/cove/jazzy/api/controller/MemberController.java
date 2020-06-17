package plus.cove.jazzy.api.controller;

import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.file.FileMetadata;
import plus.cove.infrastructure.file.UniteUploadConfig;
import plus.cove.infrastructure.file.UploadFileResult;
import plus.cove.infrastructure.file.UploadFileUtils;
import plus.cove.jazzy.api.component.BaseController;
import plus.cove.jazzy.application.StoryApplication;
import plus.cove.jazzy.domain.entity.story.Story;
import plus.cove.jazzy.domain.exception.StoryError;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
        FileMetadata metadata = UploadFileUtils.getFileMetadata(result);
        if (!metadata.hasCoordinate()) {
            return failure(StoryError.INVALID_COORDINATE);
        }

        Story story = Story.fromFile(this.getUser(), metadata);
        storyApp.save(story);

        result.setSize(file.getSize());
        result.setType(file.getContentType());
        return success(result);
    }
}
