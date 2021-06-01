package plus.cove.jazzy.domain.view;

import lombok.Getter;
import lombok.Setter;
import plus.cove.jazzy.domain.entity.account.Account;
import plus.cove.jazzy.domain.entity.user.Author;
import plus.cove.jazzy.domain.principal.UserRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * 用户注册输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
public class UserSignupInput {
    @Getter
    @Setter
    @NotEmpty(message = "账号不能为空")
    @Size(min = 4, max = 32, message = "账号4-32个字符")
    private String userName;

    @Getter
    @Setter
    @NotEmpty(message = "名称不能为空")
    @Size(min = 2, max = 32, message = "名称2-32个字符")
    private String nickName;

    @Getter
    @Setter
    @NotEmpty(message = "密码不能为空")
    @Size(min = 4, max = 32, message = "密码4-32个字符")
    private String password;

    @Getter
    private UserRequest request;

    public void withRequest(UserRequest request) {
        this.request = request;
    }

    /**
     * 转化为账号
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public Account convertToAccount() {
        return Account.create(this.userName, this.password);
    }

    /**
     * 转换为用户
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public Author convertToAuthor() {
        return Author.create(this.nickName);
    }
}
