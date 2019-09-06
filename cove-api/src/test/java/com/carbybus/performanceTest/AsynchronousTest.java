package com.carbybus.performanceTest;

import com.carbybus.infrastructure.utils.LongUtils;
import com.github.noconnor.junitperf.JUnitPerfAsyncRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.data.TestContext;
import com.github.noconnor.junitperf.reporting.providers.ConsoleReportGenerator;
import org.junit.*;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class AsynchronousTest {
    @Rule
    public JUnitPerfAsyncRule rule = new JUnitPerfAsyncRule(new ConsoleReportGenerator());

    private static ExecutorService threadPool;

    @BeforeClass
    public static void before() {
        threadPool = Executors.newFixedThreadPool(10);
    }

    @AfterClass
    public static void after() {
        threadPool.shutdownNow();
    }

    private void handle() {
        boolean isEmpty = LongUtils.isEmpty(0L);

        // 暂停
        int reset = ThreadLocalRandom.current().nextInt(0, 100);
        System.out.println(String.format("[%s] RESET: %s",
                Thread.currentThread().getName(), reset)
        );
        try {
            Thread.sleep(reset);
        } catch (InterruptedException ex) {

        }
    }

    @Test
    @JUnitPerfTest(durationMs = 10_000, rampUpPeriodMs = 1_000, maxExecutionsPerSecond = 100)
    public void asynchronous() throws InterruptedException {
        TestContext context = rule.newContext();
        threadPool.submit(() -> {
            System.out.println(String.format("[%s] START: %s",
                    Thread.currentThread().getName(), LocalDateTime.now().toString())
            );

            handle();

            System.out.println(String.format("[%s]   END: %s",
                    Thread.currentThread().getName(), LocalDateTime.now().toString())
            );
            context.success();
        });
    }
}
