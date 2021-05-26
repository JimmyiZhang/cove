package plus.cove.jazzy.api.test.business;

import org.junit.*;
import org.springframework.util.StopWatch;
import plus.cove.jazzy.api.test.BaseTest;

import java.util.stream.IntStream;

public class LockScopeTest extends BaseTest {
    private static int LOOP_COUNT = 1_000_000;

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

    public void wrong() {
        LockScopeData.reset();
        stopWatch.start("wrong");
        IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(i -> new LockScopeData().wrong());
        int counter = LockScopeData.getCounter();
        log(String.valueOf(counter));
        Assert.assertTrue(counter < LOOP_COUNT);
    }

    @Test
    public void right() {
        LockScopeData.reset();
        stopWatch.start("right");

        IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(i -> new LockScopeData().right());
        int counter = LockScopeData.getCounter();
        log(String.valueOf(counter));
        Assert.assertTrue(counter == LOOP_COUNT);
    }

    @Test
    public void atomic(){
        stopWatch.start("atomic");
        IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(i -> new LockScopeData().atomic());
        int counter = LockScopeData.getAtomic();
        log(String.valueOf(counter));
        Assert.assertTrue(counter == LOOP_COUNT);
    }

    @Test
    public void adder(){
        stopWatch.start("adder");
        IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(i -> new LockScopeData().adder());
        int counter = LockScopeData.getAdder();
        log(String.valueOf(counter));
        Assert.assertTrue(counter == LOOP_COUNT);
    }
}
