package plus.cove.infrastructure.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Redis实现分布式锁
 * 参考https://github.com/pjmike/redis-distributed-lock/
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Component
public class RedisLockUtils {
    private final Logger log = LoggerFactory.getLogger(RedisLockUtils.class);
    private static long sleepTime = 100;

    @Autowired
    private RedisTemplate<String, String> redis;

    /**
     * 加锁
     * Redis的2.6.12及以后，使用 set key value [NX] [EX] 命令
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public boolean lock(String key, String value, int timeout, TimeUnit timeUnit) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout must be greater than 0");
        }

        long seconds = timeUnit.toSeconds(timeout);
        Object result = redis.execute((RedisConnection connection) -> {
            RedisSerializer<String> serializer = redis.getStringSerializer();
            return connection.execute("set", new byte[][]{
                    serializer.serialize(key), serializer.serialize(value),
                    serializer.serialize("NX"), serializer.serialize("EX"),
                    String.valueOf(seconds).getBytes()});
        });
        return "OK".equals(result);
    }

    /**
     * 等待解锁
     *
     * @param key
     * @param value
     * @param waitTime 等待时间，单位毫秒
     * @param timeout  超时时间
     * @param timeUnit
     * @return
     * @throws InterruptedException
     */
    public boolean tryLock(String key, String value, int waitTime, int timeout, TimeUnit timeUnit) {
        if (waitTime < 0) {
            throw new IllegalArgumentException("waitTime must be greater than 0");
        }

        try {
            while (waitTime >= 0) {
                boolean lock = lock(key, value, timeout, timeUnit);
                if (!lock) {
                    return true;
                }
                waitTime -= sleepTime;
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException ex) {
            return false;
        }
        return false;
    }

    /**
     * 解锁
     * 使用Lua脚本进行解锁操纵，解锁的时候验证value值
     *
     * @param key
     * @param value
     * @return
     */
    public boolean unlock(String key, String value) {
        String lua = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end";
        DefaultRedisScript<Integer> script = new DefaultRedisScript(lua, Integer.class);
        return redis.execute(script, Collections.singletonList(key), Collections.singletonList(value)).equals(1);
    }
}
