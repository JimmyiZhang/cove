package plus.cove.jazzy.api.test.business;

import org.junit.*;
import org.springframework.util.StopWatch;
import plus.cove.jazzy.api.test.BaseTest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * 01-使用了并发工具类库，线程安全就高枕无忧了吗
 *
 * @author jimmy.zhang
 * @since 2.0
 */
public class ConcurrentHashMapPerformanceTest extends BaseTest {
    private static int LOOP_COUNT = 10_000_000;
    private static int ITEM_COUNT = 10;
    private static int POOL_COUNT = 10;

    private static StopWatch stopWatch;

    @BeforeClass
    public static void init() {
        stopWatch = new StopWatch("PERFORMANCE");
    }

    @Before
    public void before() {
        System.out.println("START TEST");
    }

    @After
    public void after() {
        stopWatch.stop();
        System.out.println("FINISH TEST");
    }

    @AfterClass
    public static void destroy() {
        String times = stopWatch.prettyPrint();
        System.out.println(times);
    }

    @Test
    public void right() throws InterruptedException {
        stopWatch.start("right");
        ConcurrentHashMap<String, LongAdder> map = new ConcurrentHashMap<>(ITEM_COUNT);
        log("init size: %d", map.size());

        ForkJoinPool pool = new ForkJoinPool(POOL_COUNT);
        pool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(i -> {
                    // 获取随机key
                    String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    map.computeIfAbsent(key, k -> new LongAdder()).increment();
                })
        );
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);

        map.forEach((k, v) -> {
            System.out.println("key" + k + ":" + v.toString());
        });
        log("finish size: %d", map.size());

        Assert.assertTrue(map.size() == ITEM_COUNT);
    }

    @Test
    public void wrong() throws InterruptedException {
        stopWatch.start("wrong");
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>(ITEM_COUNT);
        log("init size: %d", map.size());

        ForkJoinPool pool = new ForkJoinPool(POOL_COUNT);
        pool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(i -> {
                    // 获取随机key
                    String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);

                    synchronized (map) {
                        if (map.containsKey(key)) {
                            map.put(key, map.get(key) + 1);
                        } else {
                            map.put(key, 1L);
                        }
                    }
                })
        );
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);

        map.forEach((k, v) -> {
            System.out.println("key" + k + ":" + v.toString());
        });
        log("finish size: %d", map.size());

        Assert.assertTrue(map.size() == ITEM_COUNT);
    }
}
