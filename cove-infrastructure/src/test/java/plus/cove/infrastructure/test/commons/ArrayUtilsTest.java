package plus.cove.infrastructure.test.commons;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

public class ArrayUtilsTest {
    @Test
    public void toStringTest() {
        int[] numb1 = {1, 2, 3,};
        String numbs1 = ArrayUtils.toString(numb1);
        System.out.println(numbs1);

        int[] numb2 = null;
        String numbs2 = ArrayUtils.toString(numb2, "[]");
        System.out.println(numbs2);
    }

    @Test
    public void toArray() {
        String[] array = ArrayUtils.toArray("tom", "jack", "lucy");
        System.out.println(array.length);

        int[] numbs = ArrayUtils.nullToEmpty((int[]) null);
        System.out.println(numbs == null);
    }
}
