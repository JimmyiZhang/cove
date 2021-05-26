package plus.cove.jazzy.api.test;

public class BaseTest {
    public void log(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}
