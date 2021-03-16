package plus.cove.infrastructure.test.utils;

import org.junit.Test;
import org.springframework.util.Assert;
import plus.cove.infrastructure.utils.BigDecimalHelper;

import java.math.BigDecimal;

public class BigDecimalHelperTest {
    @Test
    public void round() {
        BigDecimal value1 = new BigDecimal(1.6785454);
        BigDecimal result = BigDecimalHelper.round(value1, 2);
        System.out.println(result);

        Assert.isTrue(result.equals(3), "四舍五入");
    }
}
