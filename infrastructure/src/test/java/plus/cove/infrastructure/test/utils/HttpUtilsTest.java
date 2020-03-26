package plus.cove.infrastructure.test.utils;

import plus.cove.infrastructure.http.HttpUtils;
import org.junit.Test;
import org.springframework.util.Assert;

public class HttpUtilsTest {
    @Test
    public void getTest() {
        String res = HttpUtils.get("http://www.baidu.com");
        Assert.notNull(res, "http get");
    }
}
