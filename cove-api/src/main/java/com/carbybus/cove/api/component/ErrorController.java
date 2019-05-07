package com.carbybus.cove.api.component;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.exception.ServiceError;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器错误
 * 处理非控制器异常
 * @author jimmy.zhang
 * @date 2019-05-07
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends AbstractErrorController {
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    @ResponseBody
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
        return result;
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
