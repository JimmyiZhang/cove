package plus.cove.jazzy.api.test.business;

import org.junit.Assert;
import org.junit.Test;
import plus.cove.jazzy.api.test.BaseTest;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * 01-使用了并发工具类库，线程安全就高枕无忧了吗
 *
 * @author jimmy.zhang
 * @since 2.0
 */
public class ConcurrentHashMapMisuseTest extends BaseTest {
    private static int POOL_COUNT = 10;
    private static int ITEM_COUNT = 1000;

    private ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.range(0, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(
                        i -> UUID.randomUUID().toString(),
                        Function.identity(),
                        (o1, o2) -> o1, ConcurrentHashMap::new));
    }

    @Test
    public void right() throws InterruptedException {
        ConcurrentHashMap<String, Long> map = getData(ITEM_COUNT - 100);
        log("init size: %d", map.size());

        ForkJoinPool pool = new ForkJoinPool(POOL_COUNT);
        pool.execute(() -> IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> {
                    // 符合逻辑需要加锁
                    synchronized (map) {
                        int gap = ITEM_COUNT - map.size();
                        log("map size: %d, gap size: %d", map.size(), gap);
                        map.putAll(getData(gap));
                    }
                })
        );
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        log("finish size: %d", map.size());
        Assert.assertTrue(map.size() == 1000);
    }

    @Test
    public void wrong() throws InterruptedException {
        ConcurrentHashMap<String, Long> map = getData(ITEM_COUNT - 100);
        log("init size: %d", map.size());

        ForkJoinPool pool = new ForkJoinPool(POOL_COUNT);
        pool.execute(() -> IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> {
                    int gap = ITEM_COUNT - map.size();
                    log("map size: %d, gap size: %d", map.size(), gap);
                    map.putAll(getData(gap));
                })
        );
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        log("finish size: %d", map.size());
        Assert.assertTrue(map.size() > 1000);
    }
}
