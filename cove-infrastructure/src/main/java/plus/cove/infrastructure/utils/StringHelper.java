package plus.cove.infrastructure.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串帮助类
 *
 * @author jimmy.zhang
 * @date 2019-03-08
 */
public class StringHelper {
    private static Pattern casePattern = Pattern.compile("[A-Z]");
    private static Pattern linePattern = Pattern.compile("-(\\w)");

    /**
     * 私有构造器
     */
    private StringHelper() {
    }

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 易识别字符串
     * 不包括1iIl0OozZ2
     */
    public static final String RANDOM_EASY_WORD = "aAbBcCdDeEfFgGhHjJkKLmMnNpPqQrRsStTuUvVwWxXyY3456789";

    /**
     * 用特定字符在原字符的特定位置代替原字符
     *
     * @param source     原始字符串
     * @param beginIndex 起始位置
     * @param length     结束位置
     * @param mask       替换字符串
     * @return
     * @author jimmy.zhang
     * @date 2019-07-31
     */
    public static String mask(String source, int beginIndex, int length, char mask) {
        if (beginIndex >= source.length() - 1) {
            return source;
        }

        char[] target = source.toCharArray();
        for (int i = beginIndex; i < source.length() && length > 0; i++) {
            target[i] = mask;
            length--;
        }
        return new String(target);
    }

    /**
     * 获取原字符串字串
     * null及空串返回原字符串
     * 开始位置超过字符串长度返回空串
     * 截取长度不足返回开始位置之后所有字符串
     *
     * @param source     原始字符串
     * @param startIndex 开始位置
     * @param length     截取长度
     * @return
     * @author jimmy.zhang
     * @date 2019-07-31
     */
    public static String sub(String source, int startIndex, int length) {
        if (source == null || source.isEmpty()) {
            return source;
        }

        int sourceLen = source.length() - 1;
        if (startIndex > sourceLen) {
            return EMPTY;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }
        if (length <= 0 || length > sourceLen - startIndex) {
            return source.substring(startIndex);
        }

        return source.substring(startIndex, startIndex + length);
    }

    /**
     * 分组
     * 分组后最后一位增加
     *
     * @param source
     * @param separator
     * @param length
     * @param increment
     * @return
     */
    public static String group(String source, String separator, int length, int increment) {
        if (source == null || source.isEmpty() || separator == null || separator.isEmpty()) {
            return source;
        }

        String target = EMPTY;

        int lastNum = 0;
        int lastIdx = source.lastIndexOf(separator);
        if (lastIdx == -1) {
            target = source + separator;
            lastNum = increment;
        } else {
            target = source.substring(0, lastIdx + 1);
            lastNum = Integer.valueOf(source.substring(lastIdx + 1)) + 1;
        }

        // 前补0
        StringBuilder sb = new StringBuilder(length);
        sb.append(lastNum);
        while (sb.length() < length) {
            sb.insert(0, "0");
        }

        return target + sb.toString();
    }

    /**
     * 驼峰转中线
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static String caseToMidline(String str) {
        Matcher matcher = casePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "-" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 中划线转驼峰
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static String midlineToCase(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
