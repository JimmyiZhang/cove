package plus.cove.jazzy.api.controller;

import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.file.UploadFileResult;
import plus.cove.jazzy.api.component.BaseController;

import java.io.IOException;

/**
 * 文件控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"上传"})
@RestController
@RequestMapping(value = "/file")
public class FileController extends BaseController {
    @RequestMapping(value = "upload", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ActionResult upload(@RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam(value = "name", required = false) String name) throws IOException {

        String fileName = StringUtils.isEmpty(name) ? file.getOriginalFilename() : name;
        UploadFileResult result = new UploadFileResult();

        result.setName(fileName);
        result.setSize(file.getSize());
        result.setType(file.getContentType());

        return success(result);
    }
}
