package plus.cove.infrastructure.generator;


import lombok.SneakyThrows;

/**
 * 序列号生成器
 * <p>
 * 生成每天不重复的序列号
 * worker+timestamp+sequence
 * worker       4位，支持16台机器，默认位为0
 * timestamp    秒时间戳
 * sequence     8位，支持单位时间内256个序列码，即每秒256个
 * <p>
 * 适用于没有递增要求，每天生成不重复序号，长度在9位以内
 *
 * @author jimmy.zhang
 * @since 1.1
 */
public final class DailyNumberGenerator {
    private static final int SEQUENCE_BITS = 8;
    private static final int WORKER_BITS = 4;

    private static final int SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;
    private static final int TIMESTAMP_LEFT_BITS = SEQUENCE_BITS;
    private static final int WORKER_LEFT_BITS = TIMESTAMP_LEFT_BITS + WORKER_BITS;

    private static final int MAX_WORKER_ID = 1 << WORKER_BITS;
    private static final int MAX_TOLERATE_SECONDS = 10;

    private final int workerId;
    private int sequence;
    private int lastSeconds;

    public static final DailyNumberGenerator INSTANCE = new DailyNumberGenerator();

    private DailyNumberGenerator() {
        // 获取环境变量
        String workId = System.getProperty("summer.generator.worker-id", "0");
        this.workerId = Integer.valueOf(workId).intValue();
        if (this.workerId < 0 || this.workerId >= MAX_WORKER_ID) {
            throw new IllegalArgumentException("invalid workerId");
        }
    }

    public synchronized int generate() {
        int currentSeconds = KeyGeneratorService.getSecondsInDay();
        if (waitTolerateTimeDifferenceIfNeed(currentSeconds)) {
            currentSeconds = KeyGeneratorService.getSecondsInDay();
        }

        if (0 == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
            currentSeconds = waitUntilNextTime(currentSeconds);
        }

        lastSeconds = currentSeconds;
        return (workerId << WORKER_LEFT_BITS) | (currentSeconds << TIMESTAMP_LEFT_BITS) | sequence;
    }

    @SneakyThrows
    private boolean waitTolerateTimeDifferenceIfNeed(final long currentSeconds) {
        if (lastSeconds <= currentSeconds) {
            return false;
        }
        long differenceSeconds = lastSeconds - currentSeconds;
        if (differenceSeconds < MAX_TOLERATE_SECONDS) {
            throw new IllegalStateException("clock is moving backwards");
        }
        try {
            Thread.sleep(differenceSeconds);
        } catch (InterruptedException ex) {
            throw new IllegalStateException("generator can not wait different seconds");
        }

        return true;
    }

    private int waitUntilNextTime(final long lastTime) {
        int result = KeyGeneratorService.getSecondsInDay();
        while (result <= lastTime) {
            result = KeyGeneratorService.getSecondsInDay();
        }
        return result;
    }

}