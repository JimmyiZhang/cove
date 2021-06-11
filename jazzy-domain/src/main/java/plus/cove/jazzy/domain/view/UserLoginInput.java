package plus.cove.jazzy.domain.view;

import lombok.Getter;
import lombok.Setter;
import plus.cove.jazzy.domain.principal.UserRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * 用户登录输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
public class UserLoginInput {
    @Getter
    @Setter
    @NotEmpty(message = "账号不能为空")
    @Size(min = 4, max = 32, message = "账号4-32个字符")
    private String userName;

    @Getter
    @Setter
    @NotEmpty(message = "密码不能为空")
    @Size(min = 4, max = 32, message = "密码4-32个字符")
    private String password;

    @Getter
    @Setter
    @NotEmpty(message = "消息编码不能为空")
    private String messageCode;

    @Getter
    @Setter
    @NotEmpty(message = "消息随机码不能为空")
    private String messageRandom;

    @Getter
    private UserRequest request;

    public void withRequest(UserRequest request) {
        this.request = request;
    }
}
