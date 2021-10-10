package plus.cove.infrastructure.test.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import plus.cove.infrastructure.http.HttpUtils;

@ExtendWith(SpringExtension.class)
public class HttpUtilsTest {
    @Autowired
    HttpUtils httpUtils;

    @Test
    public void getTest() {
        String res = httpUtils.get("http://www.baidu.com", null);
        Assert.notNull(res, "http get");
    }
}
