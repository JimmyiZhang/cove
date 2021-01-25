package plus.cove.infrastructure.test.utils;

import org.junit.Test;
import org.springframework.util.Assert;
import plus.cove.infrastructure.utils.BigDecimalHelper;

import java.math.BigDecimal;

public class BigDecimalHelperTest {
    @Test
    public void multiply() {
        BigDecimal value1 = new BigDecimal(1.6785454);
        Integer result = BigDecimalHelper.multiply(value1, 2);
        System.out.println(result);

        Assert.isTrue(result.equals(3), "小数乘法");
    }
}
