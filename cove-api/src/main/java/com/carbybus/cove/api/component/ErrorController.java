package com.carbybus.cove.api.component;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.exception.ServiceError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器错误
 * 处理非控制器异常
 *
 * @author jimmy.zhang
 * @date 2019-05-07
 */
@Slf4j
@RestController
@RequestMapping("/error")
public class ErrorController extends AbstractErrorController {
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @GetMapping
    public ActionResult error(HttpServletRequest request, HttpServletResponse response) {
        ActionResult<Integer> result = new ActionResult<>();

        switch (response.getStatus()) {
            case 401:
            case 402:
            case 403:
                result.fail(ServiceError.BAD_REQUEST, response.getStatus());
                break;
            case 404:
                result.fail(ServiceError.NOT_FOUND, response.getStatus());
                break;
            default:
                result.fail(ServiceError.SERVER_ERROR, response.getStatus());
                break;
        }

        String requestId = response.getHeader("Request-Code");
        log.error("请求失败, Request Code: {}", requestId);

        return result;
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
