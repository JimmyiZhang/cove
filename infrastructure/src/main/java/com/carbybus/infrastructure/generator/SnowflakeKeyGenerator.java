package com.carbybus.infrastructure.generator;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 主键生成器-雪花算法版
 *
 * @author jimmy.zhang
 * @date 2019-04-19
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
        EPOCH = LocalDateTime.of(2016, 1, 1, 0, 0)
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
        long currentMilliseconds = TimeService.getCurrentMillis();
        if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
            currentMilliseconds = TimeService.getCurrentMillis();
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
        long result = TimeService.getCurrentMillis();
        while (result <= lastTime) {
            result = TimeService.getCurrentMillis();
        }
        return result;
    }

    private void vibrateSequenceOffset() {
        sequenceOffset = (byte) (~sequenceOffset & 1);
    }

    /**
     * 时间服务
     *
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    public static class TimeService {
        private TimeService() {
        }

        /**
         * 获取当前毫秒
         *
         * @return 当前毫秒
         */
        public static long getCurrentMillis() {
            return System.currentTimeMillis();
        }
    }
}
