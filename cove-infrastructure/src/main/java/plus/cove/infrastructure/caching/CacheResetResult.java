package plus.cove.infrastructure.caching;

import lombok.Data;

import java.time.Duration;

/**
 * 缓存重置结果
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
@Data
public class CacheResetResult {
    /**
     * 名称
     */
    private String name;

    /**
     * 过期时间
     */
    private Duration expire;
}
