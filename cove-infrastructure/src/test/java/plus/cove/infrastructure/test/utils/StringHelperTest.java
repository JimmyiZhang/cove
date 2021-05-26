package plus.cove.infrastructure.test.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
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

        Assert.assertNotNull(str1);
    }

    @Test
    public void join() {
        String actual = StringUtils.joinWith("#", "1", "2", "3");
        System.out.println(actual);
        String expected = "1#2#3";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void format() {
        String actual = StringHelper.format("hello{0}world", "-");
        System.out.println(actual);
        Assert.assertEquals("hello-world", actual);
    }

    @Test
    public void format1() {
        String actual = StringHelper.format("{0}-{1}", "hello", "world");
        System.out.println(actual);
        Assert.assertEquals("hello-world", actual);
    }

    @Test
    public void format2() {
        String actual = StringHelper.format("{0}{1}{0}", "ma", "-");
        System.out.println(actual);
        Assert.assertEquals("ma-ma", actual);
    }

    @Test
    public void format3() {
        String actual = StringHelper.format("{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{11}",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
        System.out.println(actual);
        Assert.assertEquals("01234567891011", actual);
    }

    @Test
    public void format4() {
        String actual = StringHelper.format("{{hello{0}world}}", "-");
        System.out.println(actual);
        Assert.assertEquals("{hello-world}", actual);
    }
}
