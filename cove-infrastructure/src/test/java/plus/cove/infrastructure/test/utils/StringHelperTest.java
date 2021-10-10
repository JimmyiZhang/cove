package plus.cove.infrastructure.test.utils;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import plus.cove.infrastructure.utils.StringHelper;

public class StringHelperTest {
    @Test
    public void split() {
        String str1 = "1-2-3";
        System.out.println("- 分隔符返回");
        for (String str : str1.split("-")) {
            System.out.println(str);
        }

        String str2 = "1,2;3";
        System.out.println("多个分隔符返回");
        for (String str : str2.split(",|;")) {
            System.out.println(str);
        }

        Assertions.assertNotNull(str1);
    }

    @Test
    public void join() {
        String actual = StrUtil.join("#", "1", "2", "3");
        System.out.println(actual);
        String expected = "1#2#3";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void format() {
        String actual = StringHelper.format("hello{0}world", "-");
        System.out.println(actual);
        Assertions.assertEquals("hello-world", actual);
    }

    @Test
    public void format1() {
        String actual = StringHelper.format("{0}-{1}", "hello", "world");
        System.out.println(actual);
        Assertions.assertEquals("hello-world", actual);
    }

    @Test
    public void format2() {
        String actual = StringHelper.format("{0}{1}{0}", "ma", "-");
        System.out.println(actual);
        Assertions.assertEquals("ma-ma", actual);
    }

    @Test
    public void format3() {
        String actual = StringHelper.format("{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{11}",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
        System.out.println(actual);
        Assertions.assertEquals("01234567891011", actual);
    }

    @Test
    public void format4() {
        String actual = StringHelper.format("{{hello{0}world}}", "-");
        System.out.println(actual);
        Assertions.assertEquals("{hello-world}", actual);
    }
}
