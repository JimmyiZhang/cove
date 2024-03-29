package plus.cove.jazzy.application;


import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.component.PageModel;
import plus.cove.infrastructure.component.PageResult;
import plus.cove.jazzy.domain.view.AuthorListInput;
import plus.cove.jazzy.domain.view.UserLoginInput;
import plus.cove.jazzy.domain.view.UserSignupInput;

/**
 * 账号应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface AuthorApplication {
    /**
     * 注册
     *
     * @param input 注册数据
     * @return 注册结果
     * @author jimmy.zhang
     * @date 2019-03-28
     */
    ActionResult signup(UserSignupInput input);

    /**
     * 登录
     *
     * @param input 登录数据
     * @return 登录结果，含token
     * @author jimmy.zhang
     * @date 2019-05-14
     */
    ActionResult login(UserLoginInput input);

    /**
     * 获取作者
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    PageResult loadMany(AuthorListInput input, PageModel page);
}
