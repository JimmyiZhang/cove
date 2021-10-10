package plus.cove.infrastructure.test.retry;

import org.junit.jupiter.api.Test;
import plus.cove.infrastructure.retry.Retrying;
import plus.cove.infrastructure.retry.RetryingBuilder;

import java.time.LocalDateTime;

public class RetryingTest {
    @Test
    public void retrySimple() {
        Retrying retrying = RetryingBuilder.create()
                .withRetryTimes(5)
                .withBackTime(1000L)
                .build();

        retrying.execute(() -> {
            System.out.println("重试中..." + LocalDateTime.now().toString());
            return false;
        });
    }

    @Test
    public void retryRandom() {
        Retrying retrying = RetryingBuilder.create()
                .withRetryTimes(5)
                .withBackRandom(1000L, 3000L)
                .build();

        retrying.execute(() -> {
            System.out.println("重试中..." + LocalDateTime.now().toString());
            return false;
        });
    }

    @Test
    public void retryExponential() {
        Retrying retrying = RetryingBuilder.create()
                .withRetryTime(5000L)
                .withBackExponential(100L, 3000L, 2.5)
                .build();

        retrying.execute(() -> {
            System.out.println("重试中..." + LocalDateTime.now().toString());
            return false;
        });
    }
}
