package plus.cove.jazzy.domain.view;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
}
