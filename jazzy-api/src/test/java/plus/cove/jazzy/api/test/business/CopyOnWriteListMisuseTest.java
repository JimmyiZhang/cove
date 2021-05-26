package plus.cove.jazzy.api.test.business;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 01-使用了并发工具类库，线程安全就高枕无忧了吗
 *
 * @author jimmy.zhang
 * @since 2.0
 */
public class CopyOnWriteListMisuseTest {
    private static int LOOP_COUNT = 10_000;

    private static StopWatch stopWatch;

    @BeforeClass
    public static void init() {
        stopWatch = new StopWatch("PERFORMANCE");
    }

    @AfterClass
    public static void destroy() {
        String times = stopWatch.prettyPrint();
        System.out.println(times);
    }

    @Test
    public void write() {
        List<Integer> cowList = new CopyOnWriteArrayList<>();
        List<Integer> synList = Collections.synchronizedList(new ArrayList<>());

        stopWatch.start("WRITE: CopyOnWriteArrayList");
        IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(__ -> cowList.add(ThreadLocalRandom.current().nextInt(LOOP_COUNT)));
        stopWatch.stop();
        System.out.println("CopyOnWriteArrayList size: " + cowList.size());

        stopWatch.start("WRITE: SynchronizedList");
        IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(__ -> synList.add(ThreadLocalRandom.current().nextInt(LOOP_COUNT)));
        stopWatch.stop();
        System.out.println("SynchronizedList size: " + synList.size());

        Assert.assertTrue(cowList.size() == LOOP_COUNT);
    }

    @Test
    public void read() {
        List<Integer> cowList = new CopyOnWriteArrayList<>();
        cowList.addAll(IntStream.rangeClosed(1, LOOP_COUNT).boxed().collect(Collectors.toList()));
        List<Integer> synList = Collections.synchronizedList(new ArrayList<>());
        synList.addAll(IntStream.rangeClosed(1, LOOP_COUNT).boxed().collect(Collectors.toList()));

        stopWatch.start("READ: CopyOnWriteArrayList");
        IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(__ -> cowList.get(ThreadLocalRandom.current().nextInt(LOOP_COUNT)));
        stopWatch.stop();
        System.out.println("CopyOnWriteArrayList size: " + cowList.size());

        stopWatch.start("READ: SynchronizedList");
        IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(__ -> synList.get(ThreadLocalRandom.current().nextInt(LOOP_COUNT)));
        stopWatch.stop();
        System.out.println("SynchronizedList size: " + synList.size());

        Assert.assertTrue(cowList.size() == LOOP_COUNT);
    }
}
