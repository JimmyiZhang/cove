package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.file.FileUtils;
import com.carbybus.infrastructure.file.UniteUploadConfig;
import com.carbybus.infrastructure.file.UploadFileResult;
import com.carbybus.infrastructure.file.UploadFileUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

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
    @Autowired
    private UniteUploadConfig uploadConfig;

    @RequestMapping(value = "upload", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ActionResult upload(@RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam(value = "name", required = false) String name) throws IOException {

        String fileName = StringUtils.isEmpty(name) ? file.getOriginalFilename() : name;
        UploadFileResult result = UploadFileUtils.saveFile(uploadConfig.getUploadPath(), fileName, file.getInputStream());

        result.setSize(file.getSize());
        result.setType(file.getContentType());

        return success(result);
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void download(@RequestParam("name") String name) throws IOException {
        MediaType contentType = FileUtils.getMediaType(name);
        // 设置内容类型为图片格式
        this.response.setContentType(contentType.toString());
        // 设置过期时间30天，30*24*60=43200
        this.response.setHeader(HttpHeaders.CACHE_CONTROL,"max-age=43200");

        OutputStream body = response.getOutputStream();
        byte[] byteFile = UploadFileUtils.loadFile(uploadConfig.getUploadPath(), name);
        body.write(byteFile);
        body.flush();
    }
}
