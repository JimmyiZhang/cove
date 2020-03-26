package plus.cove.jazzy.application;


import plus.cove.infrastructure.component.ActionResult;
import plus.cove.jazzy.domain.view.UserActiveInput;
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
     * 激活用户
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-25
     */
    ActionResult active(UserActiveInput input);
}
