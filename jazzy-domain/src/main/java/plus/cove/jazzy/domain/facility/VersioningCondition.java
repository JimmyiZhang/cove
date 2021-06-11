package plus.cove.jazzy.domain.facility;

import lombok.Data;

/**
 * 版本条件
 *
 * @author jimmy.zhang
 * @since 1.1
 */
@Data
public class VersioningCondition {
    /**
     * 目标
     */
    private String target;

    /**
     * 私有构造函数
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    private VersioningCondition() {
    }

    public static VersioningCondition from(String target) {
        VersioningCondition config = new VersioningCondition();
        config.target = target;
        return config;
    }
}
