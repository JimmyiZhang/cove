package plus.cove.jazzy.api.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.exception.BusinessError;
import plus.cove.infrastructure.json.JsonUtils;
import plus.cove.jazzy.application.UserApplication;
import plus.cove.jazzy.domain.principal.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 控制器基类
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class BaseController {
    private final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected JsonUtils jsonUtils;
    @Autowired
    protected UserApplication userApp;

    /**
     * 获取当前用户
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-08-19
     */
    protected UserPrincipal getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String claim = auth.getName();
        log.debug("the user authentication is : {}", claim);

        if (auth instanceof AnonymousAuthenticationToken) {
            return UserPrincipal.UNKNOWN;
        } else {
            Long userId = Long.parseLong(claim);

            // 获取用户信息
            UserPrincipal user = userApp.findPrincipal(userId);
            return user;
        }
    }

    /**
     * 输出缓存
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    protected void responseCache(int maxAge) {
        if (maxAge < 0) {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
        } else {
            response.setHeader("Cache-Control", "max-age=" + String.valueOf(maxAge));
        }
    }

    /**
     * 输入状态
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    protected void responseStatus(HttpStatus status) {
        response.setStatus(status.value());
    }

    /**
     * 输出特定类型
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    protected void responseContent(String mediaType) {
        response.setContentType(mediaType);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    /**
     * 输出json
     *
     * @param object
     */
    protected void responseJson(Object object) {
        String json = jsonUtils.toJson(object);
        try {
            response.getWriter().print(json);
        } catch (IOException ex) {
            log.error("response输出失败", ex);
        }
    }

    /**
     * 成功
     *
     * @param data 需要返回的数据
     * @return 携带返回数据的操作成功结果
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public <T> ActionResult<T> success(T data) {
        ActionResult<T> result = ActionResult.success();
        result.succeed(data);

        return result;
    }

    /**
     * 成功
     *
     * @return 表示操作成功的结果
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public ActionResult success() {
        return ActionResult.success();
    }

    /**
     * 失败
     *
     * @param error 错误
     * @return 携带错误码和错误消息的操作失败结果
     * @author jimmy.zhang
     * @date 2019-02-26
     */
    public ActionResult failure(BusinessError error) {
        return ActionResult.failure(error);
    }
}
