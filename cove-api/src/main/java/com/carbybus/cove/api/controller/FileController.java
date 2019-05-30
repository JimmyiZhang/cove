package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"上传"})
@RestController
@RequestMapping(value = "/files")
public class FileController extends BaseController {
    @RequestMapping(value = "upload", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public void upload(@RequestParam(value = "file", required = false) MultipartFile[] files) {


        System.out.println("ok");
    }
}
