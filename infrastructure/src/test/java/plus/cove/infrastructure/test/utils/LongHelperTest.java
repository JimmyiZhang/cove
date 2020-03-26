package plus.cove.infrastructure.test.utils;

import org.junit.Test;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;

public class LongHelperTest {
    @Test
    public void duration() {
        LocalDateTime date1 = LocalDateTime.of(2020, 2, 21,13,37,0);
        LocalDateTime date2 = LocalDateTime.of(2020,4, 22,12,0,0);

        int days =(int) Duration.between(date1, date2).toDays();
        System.out.println(days);
        Assert.isTrue(days == 15, "duration");
    }
}
