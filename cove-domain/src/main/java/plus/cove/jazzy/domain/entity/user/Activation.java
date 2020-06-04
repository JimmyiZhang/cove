package plus.cove.jazzy.domain.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultEntity;

import java.time.LocalDateTime;

/**
 * 激活码
 *
 * @author jimmy.zhang
 * @since 1.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "activation")
public class Activation extends DefaultEntity {
    private String userCode;
    private String authCode;
    private LocalDateTime createTime;
    private LocalDateTime expiredTime;

    /**
     * 创建用户激活实体
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-24
     */
    public static Activation create(String userCode, String authCode) {
        Activation entity = new Activation();
        entity.valueOf();

        LocalDateTime createTime = LocalDateTime.now();
        LocalDateTime expiredTime = createTime.plusMinutes(15);
        entity.createTime = createTime;
        entity.expiredTime = expiredTime;
        entity.userCode = userCode;
        entity.authCode = authCode;
        return entity;
    }

    /**
     * 是否有效
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-25
     */
    public boolean isValid() {
        return this.expiredTime.isAfter(LocalDateTime.now());
    }
}
