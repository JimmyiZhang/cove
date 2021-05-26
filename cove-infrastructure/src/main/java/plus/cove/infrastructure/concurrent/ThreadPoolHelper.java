package plus.cove.infrastructure.concurrent;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池帮助类
 *
 * @author jimmy.zhang
 * @since 2.0
 */
public class ThreadPoolHelper {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            10, 50, 2, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new BasicThreadFactory.Builder().namingPattern("cove-thread-pool-%d").build().getWrappedFactory());

    public static ThreadPoolExecutor current() {
        return executor;
    }
}
