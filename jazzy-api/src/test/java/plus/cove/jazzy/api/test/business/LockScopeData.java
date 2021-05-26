package plus.cove.jazzy.api.test.business;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class LockScopeData {
    private static int counter = 0;
    private static AtomicInteger atomicCounter = new AtomicInteger(0);
    private static LongAdder adderCounter = new LongAdder();
    private static Object locker = new Object();

    public static int reset() {
        counter = 0;
        atomicCounter.setRelease(0);
        adderCounter.reset();
        return counter;
    }

    public synchronized void wrong() {
        counter++;
    }

    public void right() {
        synchronized (locker) {
            counter++;
        }
    }

    public static int getCounter(){
        return counter;
    }

    public static int getAtomic(){
        return atomicCounter.get();
    }

    public static int getAdder(){
        return adderCounter.intValue();
    }

    public void atomic(){
        atomicCounter.getAndIncrement();
    }

    public void adder(){
        adderCounter.increment();
    }
}
