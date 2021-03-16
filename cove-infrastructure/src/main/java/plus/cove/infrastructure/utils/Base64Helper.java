package plus.cove.infrastructure.utils;

/**
 * base64工具类
 *
 * @author jimmy.zhang
 * @date 2019-04-04
 */
public class Base64Helper {
    // base64格式
    private static final String PATTERN_BASE64 = "base64,";

    /**
     * 私有构造器
     */
    private Base64Helper() {
    }

    public static String trim(String base64) {
        if (base64 == null || base64.equals("") || base64.length() < PATTERN_BASE64.length()) {
            return base64;
        }

        int index = base64.indexOf(PATTERN_BASE64) + PATTERN_BASE64.length();
        int length = base64.length();

        return base64.substring(index, length);
    }
}
