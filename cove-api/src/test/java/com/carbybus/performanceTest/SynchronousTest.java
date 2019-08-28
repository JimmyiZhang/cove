package com.carbybus.performanceTest;

import com.carbybus.infrastructure.utils.LongUtils;
import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.reporting.providers.ConsoleReportGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalDateTime;

public class SynchronousTest {
    @Rule
    public JUnitPerfRule rule = new JUnitPerfRule(new ConsoleReportGenerator());

    @Before
    public void before() {
        String name = String.format("[%s] S: %s",
                Thread.currentThread().getName(),
                LocalDateTime.now().toString());
        System.out.println(name);
    }

    @After
    public void after() {
        String name = String.format("[%s] E: %s",
                Thread.currentThread().getName(),
                LocalDateTime.now().toString());
        System.out.println(name);
    }

    @Test
    @JUnitPerfTest(threads = 5, durationMs = 10_000, maxExecutionsPerSecond = 2)
    public void synchronous() {
        boolean isEmpty = LongUtils.isEmpty(0L);

        String name = String.format("[%s] R: isEmpty: %s",
                Thread.currentThread().getName(),
                isEmpty);
        System.out.println(name);
    }
}
