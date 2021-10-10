package plus.cove.infrastructure.test.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import plus.cove.infrastructure.utils.BigDecimalHelper;

import java.math.BigDecimal;

public class BigDecimalHelperTest {
    @Test
    public void round() {
        BigDecimal value1 = new BigDecimal(1.6785454);
        BigDecimal result = BigDecimalHelper.round(value1, 2);
        System.out.println(result);

        Assertions.assertTrue(result.equals(3), "四舍五入");
    }
}
