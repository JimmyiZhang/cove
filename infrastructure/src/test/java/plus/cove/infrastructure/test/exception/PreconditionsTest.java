package plus.cove.infrastructure.test.exception;

import org.junit.Test;
import plus.cove.infrastructure.exception.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * 预检测试
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class PreconditionsTest {
    @Test(expected = IllegalArgumentException.class)
    public void assertTrue(){
        Preconditions.assertTrue(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assertEmpty(){
        List<String> list = new ArrayList<>();
        list.add("list");
        Preconditions.assertEmpty(list);
    }
}
