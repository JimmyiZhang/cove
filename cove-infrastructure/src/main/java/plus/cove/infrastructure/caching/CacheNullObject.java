package plus.cove.infrastructure.caching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.Callable;

/**
 * 缓存空对象
 * 有效期
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
