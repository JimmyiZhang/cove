package plus.cove.infrastructure.caching;

import lombok.Data;

import java.time.Instant;

/**
 * 缓存空对象
 * <p>
 * 可指定有效期
 *
 * @author jimmy.zhang
 * @since 1.1
 */
@Data
public class CacheNullObject {
    /**
     * 有效时间
     */
    private Instant expiredTime;
}
