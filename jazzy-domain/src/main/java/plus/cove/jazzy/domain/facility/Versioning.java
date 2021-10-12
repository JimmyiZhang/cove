package plus.cove.jazzy.domain.facility;

import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 版本
 * <p>
 * 用于执行一次限制使用，比如消费，充值
 * 用于去重使用，比如重复提交，存在有效时间
 *
 * @author jimmy.zhang
 * @since 1.1
 */
@Data
@Entity
@Table(name = "facility_versioning")
@EqualsAndHashCode(callSuper = true)
public class Versioning extends DefaultEntity {
    /**
     * 目标
     */
    private String target;
    /**
     * 随机数
     */
    private String random;

    /**
     * 状态
     */
    private VersioningState state;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 失效日期
     */
    private LocalDateTime expiredTime;

    /**
     * 使用给定目标创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static Versioning create(String target, String random) {
        Versioning entity = new Versioning();
        entity.valueOf();
        entity.setTarget(target);
        entity.setRandom(random);
        entity.setState(VersioningState.VALID);

        LocalDateTime now = LocalDateTime.now();
        entity.setCreateTime(now);
        entity.setExpiredTime(now.plusMinutes(5));

        return entity;
    }
}
