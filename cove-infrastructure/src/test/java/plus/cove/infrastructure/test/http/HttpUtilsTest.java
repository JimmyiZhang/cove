package plus.cove.infrastructure.test.http;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import plus.cove.infrastructure.http.HttpUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class HttpUtilsTest {
    @Autowired
    HttpUtils httpUtils;

    @Test
    public void getTest() {
        String res = httpUtils.get("http://www.baidu.com", null);
        Assert.notNull(res, "http get");
    }
}
