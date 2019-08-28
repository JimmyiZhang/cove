package com.carbybus.performanceTest;

import com.carbybus.infrastructure.utils.LongUtils;
import com.github.noconnor.junitperf.JUnitPerfAsyncRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.data.TestContext;
import com.github.noconnor.junitperf.reporting.providers.ConsoleReportGenerator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

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
        threadPool = Executors.newFixedThreadPool(5);
    }

    @AfterClass
    public static void after() {
        threadPool.shutdownNow();
    }

    @Test
    @JUnitPerfTest(durationMs = 10_000, maxExecutionsPerSecond = 2)
    public void asynchronous() throws InterruptedException {
        TestContext context = rule.newContext();
        threadPool.submit(() -> {
            System.out.println(String.format("[%s] START: %s",
                    Thread.currentThread().getName(), LocalDateTime.now().toString())
            );

            boolean isEmpty = LongUtils.isEmpty(0L);

            // 随机暂停
            int reset = ThreadLocalRandom.current().nextInt(0, 100);
            System.out.println(String.format("[%s] RESET: %s",
                    Thread.currentThread().getName(), reset)
            );
            try {
                Thread.sleep(reset);
            } catch (InterruptedException ex) {

            }

            context.success();

            System.out.println(String.format("[%s]   END: %s",
                    Thread.currentThread().getName(), LocalDateTime.now().toString())
            );
        });
    }
}
