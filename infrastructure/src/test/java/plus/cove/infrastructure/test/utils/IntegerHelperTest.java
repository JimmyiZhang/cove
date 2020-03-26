package plus.cove.infrastructure.test.utils;

import org.junit.Test;
import org.springframework.util.Assert;

public class IntegerHelperTest {
    @Test
    public void format() {
        Integer origin = 1;

        String expected = "+1";
        String actual = String.format("%+d", origin);

        System.out.println(String.format("%+d", 10));
        System.out.println(String.format("%+d", -10));
        System.out.println(String.format("%04d", 10));
        Assert.isTrue(actual.equals(expected), "数字格式化");
    }
}
