package plus.cove.jazzy.api.test.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

public class ConcurrentTest {
    @Test
    public void threadList() throws InterruptedException {
        ConcurrentList object = new ConcurrentList();
        Thread t1 = new Thread(() -> {
            object.run("1-", 100);
        });
        Thread t2 = new Thread(() -> {
            object.run("2-", 100);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        object.print();
        Assert.assertTrue(object.size() < 200);
    }

    @Test
    public void generalIncrease() throws InterruptedException {
        ConcurrentObject object = new ConcurrentObject();
        Thread t1 = new Thread(() -> {
            object.generalIncrease(100);
        });
        Thread t2 = new Thread(() -> {
            object.generalIncrease(100);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        object.print();
        Assert.assertTrue(object.generalValue() < 200);
    }

    @Test
    public void atomicIncrease() throws InterruptedException {
        ConcurrentObject object = new ConcurrentObject();
        Thread t1 = new Thread(() -> {
            object.atomicIncrease(100);
        });
        Thread t2 = new Thread(() -> {
            object.atomicIncrease(100);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        object.print();
        Assert.assertTrue(object.atomicValue() == 200);
    }

    @Test
    public void threadPool() throws InterruptedException {
        ConcurrentObject object = new ConcurrentObject();
        Thread t1 = new Thread(() -> {
            object.atomicIncrease(100);
        });
        Thread t2 = new Thread(() -> {
            object.atomicIncrease(100);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        object.print();
        Assert.assertTrue(object.atomicValue() == 200);
    }

    @Test
    public void customerPool() throws IOException {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new CustomerThreadFactory();
        RejectedExecutionHandler handler = new CustomerRejectedPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        // 预启动所有核心线程
        executor.prestartAllCoreThreads();
        for (int i = 1; i <= 10; i++) {
            CustomerThreadTask task = new CustomerThreadTask(String.valueOf(i));
            executor.execute(task);
        }

        // 阻塞主线程
        System.in.read();
    }
}
