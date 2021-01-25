package plus.cove.jazzy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import plus.cove.infrastructure.file.FileUtils;
import plus.cove.infrastructure.logger.LoggerFront;
import plus.cove.jazzy.api.component.BaseController;

import java.io.IOException;
import java.io.InputStream;

/**
 * 首页控制器
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Api(value = "home", tags = "首页")
@RestController
@RequestMapping(value = "/")
public class HomeController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "请求根目录", notes = "用于获取网站状态")
    @RequestMapping(value = "", method = {RequestMethod.HEAD, RequestMethod.GET})
    public void homeIndex() {
    }

    @ApiOperation(value = "请求txt文件", notes = "用于第三方验证")
    @RequestMapping(value = "{path}.txt", method = RequestMethod.GET, produces = "text/plain")
    public void plainText(@PathVariable("path") String path) throws IOException {
        // 设置输出参数
        responseContent(MediaType.TEXT_PLAIN_VALUE);

        // 获取输入流
        InputStream stream = this.getClass().getResourceAsStream("/config/" + path + ".txt");
        byte[] bytes = FileUtils.copyStream(stream).toByteArray();
        response.getOutputStream().write(bytes);
    }

    @ApiOperation(value = "前端错误日志", notes = "前端错误日志")
    @RequestMapping(value = "/frog", method = RequestMethod.POST)
    public void frontLog(@RequestBody LoggerFront frog) {
        log.error("frog: {}", frog);
    }
}
