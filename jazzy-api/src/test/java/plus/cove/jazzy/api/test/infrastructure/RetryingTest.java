package plus.cove.jazzy.api.test.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import plus.cove.infrastructure.retry.Retrying;
import plus.cove.infrastructure.retry.RetryingBuilder;
import plus.cove.jazzy.api.ApiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class RetryingTest {
    @Test
    public void retry() {
        Retrying retrying = RetryingBuilder.create()
                .withRetryTimes(4)
                .withBackTime(1000L)
                .build();

        retrying.execute(() -> {
            System.out.println("重试中...");
            return false;
        });
    }
}
