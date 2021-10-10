package plus.cove.infrastructure.test.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import plus.cove.infrastructure.utils.Base64Helper;

public class Base64HelperTest {
    @Test
    public void trim() {
        String base64 = "data:image/jpeg;base64,/9j/4AAQSkZJRg";
        String actual = Base64Helper.trim(base64);
        String expected = "/9j/4AAQSkZJRg";

        Assertions.assertEquals("相等", expected, actual);
    }
}
