package plus.cove.jazzy.domain.facility;

import cn.hutool.core.util.RandomUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 版本对象
 *
 * @author jimmy.zhang
 * @since 1.1
 */
public class VersioningTarget {
    /**
     * 目标
     */
    @Getter
    private String code;

    /**
     * token
     */
    @Getter
    private String random;

    @Getter
    @Setter
    @JsonIgnore
    private LocalDateTime expiredTime;

    /**
     * 验证随机数
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static boolean valid(VersioningTarget target, String random) {
        if (target == null) {
            return false;
        }
        return target.getExpiredTime().isBefore(LocalDateTime.now()) ||
                !Objects.equals(target.getRandom(), random);
    }

    public static VersioningTarget from(Versioning entity) {
        VersioningTarget target = new VersioningTarget();
        target.code = entity.getTarget();
        target.random = entity.getRandom();
        target.expiredTime = entity.getExpiredTime();
        return target;
    }

    public static VersioningTarget codeOf(String code) {
        VersioningTarget target = new VersioningTarget();
        target.code = code;
        target.random = RandomUtil.randomString (16);
        return target;
    }

    public static VersioningTarget of() {
        String code = RandomUtil.randomString (32);
        return codeOf(code);
    }
}
