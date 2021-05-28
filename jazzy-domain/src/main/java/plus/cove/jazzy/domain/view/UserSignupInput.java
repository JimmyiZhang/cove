package plus.cove.jazzy.domain.view;

import plus.cove.jazzy.domain.entity.account.Account;
import plus.cove.jazzy.domain.entity.user.Author;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * 用户注册输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
public class UserSignupInput {
    @NotEmpty(message = "账号不能为空")
    @Size(min = 4, max = 32, message = "账号4-32个字符")
    private String userMail;

    @NotEmpty(message = "名称不能为空")
    @Size(min = 3, max = 32, message = "名称3-32个字符")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 3, max = 32, message = "密码3-32个字符")
    private String password;

    /**
     * 转化为账号
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public Account convertToAccount() {
        return Account.create(this.userMail, this.password);
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
        return Author.create(this.userName);
    }
}
