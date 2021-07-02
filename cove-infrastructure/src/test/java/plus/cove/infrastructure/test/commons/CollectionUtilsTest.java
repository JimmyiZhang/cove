package plus.cove.infrastructure.test.commons;

import org.junit.Assert;
import org.junit.Test;
import plus.cove.infrastructure.jwt.JwtResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtilsTest {
    @Test
    public void comparingInt() {
        List<String> list = new ArrayList<>();
        list.add("jimmy");
        list.add("amy");
        list.add("lily");

        Collections.sort(list, (item1, item2) -> item1.length() - item2.length());
        Collections.sort(list, Comparator.comparingInt(String::length));
        list.sort(Comparator.comparingInt(String::length));
        for (String ls : list) {
            System.out.println(ls);
        }

        Assert.assertTrue(list.size() == 3);
    }

    @Test
    public void comparingCustomer() {
        // 借用
        List<JwtResult> tokens = new ArrayList<>();
        tokens.add(new JwtResult("A", 4));
        tokens.add(new JwtResult("B", 3));
        tokens.add(new JwtResult("C", 3));
        tokens.add(new JwtResult("D", 1));
        tokens.add(new JwtResult("E", 1));
        tokens.add(new JwtResult("F", 6));

        List<JwtResult> alternate = tokens.stream()
                .sorted(Comparator.comparing(JwtResult::getExpire).thenComparing(JwtResult::getToken, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        for (JwtResult jr : alternate) {
            System.out.println(jr.getToken());
        }

        Assert.assertTrue(alternate.size() == 6);
    }
}
