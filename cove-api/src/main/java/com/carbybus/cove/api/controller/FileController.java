package com.carbybus.cove.api.controller;

import io.swagger.annotations.Api;
import me.desair.tus.server.TusFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"用户"})
@RestController
@RequestMapping(value = "/files")
public class FileController {
    @Autowired
    TusFileUploadService tusFileUploadService;

    @RequestMapping(value = "upload", method = {RequestMethod.POST, RequestMethod.PATCH, RequestMethod.HEAD,
            RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.GET})
    public void upload(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse) throws IOException {
        tusFileUploadService.process(servletRequest, servletResponse);
    }
}
