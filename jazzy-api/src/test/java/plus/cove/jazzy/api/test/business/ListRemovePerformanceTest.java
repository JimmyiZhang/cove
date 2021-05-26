package plus.cove.jazzy.api.test.business;

import org.junit.*;
import org.springframework.util.StopWatch;
import plus.cove.jazzy.api.test.BaseTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 01-使用了并发工具类库，线程安全就高枕无忧了吗
 *
 * @author jimmy.zhang
 * @since 2.0
 */
public class ListRemovePerformanceTest extends BaseTest {
    private static int ITEM_COUNT = 100_000;
    private static int ITEM_REMOVE = 13;
    List<Integer> list = null;

    private static StopWatch stopWatch;

    @BeforeClass
    public static void init() {
        stopWatch = new StopWatch("PERFORMANCE");
    }

    @Before
    public void before() {
        System.out.println("INITIAL DATA");
        list = IntStream.rangeClosed(1, ITEM_COUNT)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
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
    public void removeWithFor() {
        stopWatch.start("REMOVE WITH FOR");
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).intValue() % 13 == 0) {
                list.remove(i);
            }
        }
        System.out.println(list.size());
    }

    @Test
    public void removeWithIterator() {
        stopWatch.start("REMOVE WITH ITERATOR");
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().intValue() % 13 == 0) {
                iterator.remove();
            }
        }
        System.out.println(list.size());
    }

    @Test
    public void removeWithRemoveIf() {
        stopWatch.start("REMOVE WITH REMOVE IF");
        list.removeIf(l -> l.intValue() % 13 == 0);
        System.out.println(list.size());
    }

    @Test
    public void removeWithStream() {
        stopWatch.start("REMOVE WITH STREAM");
        List other = list.stream()
                .filter(l -> l.intValue() % 13 != 0)
                .collect(Collectors.toList());
        System.out.println(other.size());
    }
}
