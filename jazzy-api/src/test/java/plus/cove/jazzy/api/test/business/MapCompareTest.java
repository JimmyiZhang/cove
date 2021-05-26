package plus.cove.jazzy.api.test.business;

import org.junit.Test;
import plus.cove.jazzy.api.test.BaseTest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class MapCompareTest extends BaseTest {
    private void test(Map<String, String> map) {
        String className = map.getClass().getName();
        log("==========================================================");
        log("CLASS NAME: " + className);

        // putIfAbsent
        if (className.equals("java.util.HashMap")) {
            // ConcurrentHashMap，key和value均不能为空
            log("putIfAbsent, null value is: " + map.putIfAbsent("test1", null));
        }
        log("containsKey, after putIfAbsent: " + map.containsKey("test1"));
        log("computeIfAbsent, value is: " + map.computeIfAbsent("test2", k -> null));
        log("containsKey, after computeIfAbsent: " + map.containsKey("test2"));

        log("putIfAbsent non-null value: %s", map.putIfAbsent("test3", "test3"));
        log("computeIfAbsent non-null value: %s", map.computeIfAbsent("test4", k -> "test4"));


        log("putIfAbsent expensive value: %s", map.putIfAbsent("test4", getValue()));
        log("computeIfAbsent expensive value: %s", map.computeIfAbsent("test4", k -> getValue()));
    }

    private static String getValue() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return UUID.randomUUID().toString();
    }

    @Test
    public void testMap() {
        test(new HashMap<>());
        test(new ConcurrentHashMap<>());
    }
}
