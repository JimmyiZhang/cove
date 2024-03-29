package plus.cove.jazzy.api.component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.exception.ServiceError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器错误
 * 处理非控制器异常
 *
 * @author jimmy.zhang
 * @date 2019-05-07
 */
@RestController
@RequestMapping("/error")
public class ErrorController extends AbstractErrorController {
    private final Logger log = LoggerFactory.getLogger(ErrorController.class);

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping(value = "")
    public ActionResult error(HttpServletRequest request, HttpServletResponse response) {
        ActionResult<Integer> result = ActionResult.result();

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

        log.error("Request Log ==> code: {}, method: {}",
                response.getHeader("Request-Code"), request.getMethod());
        response.setStatus(HttpServletResponse.SC_OK);
        return result;
    }
}
