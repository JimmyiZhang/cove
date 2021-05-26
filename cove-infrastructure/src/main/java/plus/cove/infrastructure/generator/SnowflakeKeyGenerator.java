package plus.cove.infrastructure.generator;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 主键生成器-雪花算法版
 * <p>
 * 生成不重复的序列号（从自定义时间开始）
 * timestamp+worker+sequence
 * timestamp    毫秒时间戳
 * worker       10位，支持1024台机器，默认位为0
 * sequence     12位，支持1毫秒内4096个序列码
 *
 * 适用于生成全局序号，全局非连续递增，19位
 * 尾数奇偶分散分布
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public final class SnowflakeKeyGenerator implements KeyGenerator {
    public static final long EPOCH;

    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_BITS = 10L;

    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1L;

    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;

    private static final int MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS = 10;

    private final long workerId;

    private byte sequenceOffset;

    private long sequence;

    private long lastMilliseconds;

    static {
        EPOCH = LocalDateTime.of(2020, 1, 1, 0, 0)
                .toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    public SnowflakeKeyGenerator() {
        this(0);
    }

    public SnowflakeKeyGenerator(final long workerId) {
        if (workerId < 0 || workerId >= WORKER_ID_MAX_VALUE) {
            throw new IllegalArgumentException("Invalid workerId");
        }
        this.workerId = workerId;
    }

    @Override
    public String getType() {
        return "SNOWFLAKE";
    }

    @Override
    public synchronized long generateKey() {
        long currentMilliseconds = KeyGeneratorService.getCurrentMillis();
        if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
            currentMilliseconds = KeyGeneratorService.getCurrentMillis();
        }
        if (lastMilliseconds == currentMilliseconds) {
            if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
                currentMilliseconds = waitUntilNextTime(currentMilliseconds);
            }
        } else {
            vibrateSequenceOffset();
            sequence = sequenceOffset;
        }
        lastMilliseconds = currentMilliseconds;
        return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    @SneakyThrows
    private boolean waitTolerateTimeDifferenceIfNeed(final long currentMilliseconds) {
        if (lastMilliseconds <= currentMilliseconds) {
            return false;
        }
        long timeDifferenceMilliseconds = lastMilliseconds - currentMilliseconds;
        if (timeDifferenceMilliseconds < MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS) {
            throw new IllegalStateException("Clock is moving backwards");
        }

        Thread.sleep(timeDifferenceMilliseconds);
        return true;
    }

    private long waitUntilNextTime(final long lastTime) {
        long result = KeyGeneratorService.getCurrentMillis();
        while (result <= lastTime) {
            result = KeyGeneratorService.getCurrentMillis();
        }
        return result;
    }

    private void vibrateSequenceOffset() {
        sequenceOffset = (byte) (~sequenceOffset & 1);
    }

}
