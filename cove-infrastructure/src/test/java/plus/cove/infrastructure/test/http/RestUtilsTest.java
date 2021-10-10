package plus.cove.infrastructure.test.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.http.RestUtils;
import plus.cove.infrastructure.http.UniteHttpConfig;
import plus.cove.infrastructure.json.UniteJsonConfig;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        UniteHttpConfig.class,
        UniteJsonConfig.class,
        RestUtils.class,
        RestTemplateAutoConfiguration.class},
        initializers = ConfigDataApplicationContextInitializer.class)
public class RestUtilsTest {
    @Autowired
    RestUtils restUtils;
    @Autowired
    UniteJsonConfig jsonConfig;

    @Test
    public void getForObject() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Product-Code", "A20001");

        ActionResult result = restUtils.getObject(
                "http://101.200.53.244:8090/activation/message?phone=13911006493",
                headers,
                ActionResult.class);
        System.out.println(result);
        Assert.isTrue(result.isSuccess(), "get");
    }

    @Test
    public void postObject() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Product-Code", "A20001");

        RestObject expected = new RestObject();
        expected.setDateValue(LocalDateTime.now());
        expected.setIntValue(123);
        expected.setLongValue(123456L);
        RestObject actual = restUtils.postObject(
                "http://101.200.53.244:8090/activation/left?api-token=token",
                null,
                headers,
                RestObject.class);
        System.out.println(actual);
        Assert.notNull(actual, "post");
    }
}
