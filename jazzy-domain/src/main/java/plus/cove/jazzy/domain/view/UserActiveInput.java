package plus.cove.jazzy.domain.view;

import lombok.Data;
import plus.cove.jazzy.domain.entity.account.Account;

import javax.validation.constraints.NotEmpty;

/**
 * 用户激活输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
public class UserActiveInput {
    @NotEmpty(message = "激活码不能为空")
    private String authCode;

    public Account toAccount(Long userId) {
        Account entity = new Account();
        entity.setId(userId);
        entity.active();
        return entity;
    }
}
