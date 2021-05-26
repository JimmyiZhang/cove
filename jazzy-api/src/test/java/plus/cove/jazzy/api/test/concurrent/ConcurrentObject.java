package plus.cove.jazzy.api.test.concurrent;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentObject {
    private static Integer generalValue = 0;
    private static AtomicInteger atomicValue = new AtomicInteger(0);

    // 自增
    public void generalIncrease(int max) {
        for (int i = 0; i < max; i++) {
            try {
                Thread.sleep(new Random().nextInt(2));
            } catch (InterruptedException ie) {

            }

            generalValue = generalValue.intValue() + 1;
        }
    }

    public void atomicIncrease(int max) {
        for (int i = 0; i < max; i++) {
            try {
                Thread.sleep(new Random().nextInt(2));
            } catch (InterruptedException ie) {

            }

            atomicValue.getAndIncrement();
        }
    }

    public int generalValue() {
        return generalValue.intValue();
    }

    public int atomicValue() {
        return atomicValue.get();
    }

    public void print() {
        System.out.println("general value：" + generalValue.intValue());
        System.out.println("atomic value：" + atomicValue.get());
    }
}
