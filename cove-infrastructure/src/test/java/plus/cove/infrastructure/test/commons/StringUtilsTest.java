package plus.cove.infrastructure.test.commons;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void truncate() {
        String origin = "hello-world";
        String actual = StringUtils.truncate(origin, 5, 10);
        System.out.println(actual);
    }
}
