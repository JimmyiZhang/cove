package plus.cove.jazzy.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册事件
 * <p>
 * 自定义事件
 *
 * @author jimmy.zhang
 * @since 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {
    private String userMail;
}
