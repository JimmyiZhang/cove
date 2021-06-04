package plus.cove.jazzy.domain.entity.limiting;

import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * 限流
 * <p>
 * 比如短信限流
 * 登陆错误次数限制
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Limiting extends DefaultEntity {
    /**
     * 对象
     * <p>
     * 比如针对短信：手机号，IP地址
     */
    private String target;
    /**
     * 类别
     * <p>
     * LimitingConfig中设置的类别
     */
    private String category;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
