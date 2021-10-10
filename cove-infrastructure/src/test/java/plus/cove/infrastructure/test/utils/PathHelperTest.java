package plus.cove.infrastructure.test.utils;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.nio.file.Paths;

public class PathHelperTest {
    @Test
    public void pathGet() {
        String path = "hello.txt";
        String expected = "config\\hello.txt";

        String actual = Paths.get("config", path).toString();
        System.out.println(actual);
        Assert.isTrue(actual.equals(expected), "路径");
    }
}
