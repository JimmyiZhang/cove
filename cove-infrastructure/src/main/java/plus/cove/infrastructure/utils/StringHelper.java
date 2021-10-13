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
    private static final char FORMAT_BEGIN = '{';
    private static final char FORMAT_END = '}';
    private static final char CHAR_UNDERLINE = '_';

    private static final Pattern casePattern = Pattern.compile("[A-Z]");
    private static final Pattern linePattern = Pattern.compile("-(\\w)");

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
     * 格式化
     * <p>
     * 使用方法：
     * StringUtils.format("{0}-{1}","hello", "world");
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static String format(String format, String... args) {
        if (null == format || args == null || args.length == 0) {
            return null;
        }

        // 计算最大长度
        int len = format.length();
        int max = len;
        for (String arg : args) {
            max += arg.length();
        }

        // 初始化结果
        char[] charArr = new char[max];

        int index = 0;
        int length = 0;
        char cChar = '\0';
        char nChar = '\0';

        // 数字模式（大于-1）
        int number = -1;

        // 拼接字符串
        while (index < len) {
            // 取当前字符和下一个字符
            cChar = (nChar == '\0' ? format.charAt(index) : nChar);
            nChar = index < len - 1 ? format.charAt(index + 1) : '\0';

            // 转义符
            boolean isEscape = (cChar == FORMAT_BEGIN || cChar == FORMAT_END);
            if (isEscape && cChar == nChar) {
                charArr[length] = cChar;
                length++;
                index += 2;
                nChar = '\0';
                continue;
            }

            // 数字模式，匹配数字
            if (number > -1) {
                if (nChar == FORMAT_END) {
                    String arg = args[number];
                    for (char ac : arg.toCharArray()) {
                        charArr[length] = ac;
                        length++;
                    }
                    number = -1;
                } else {
                    number = number * 10 + nChar - 48;
                }
                index++;
                continue;
            }

            // 匹配结尾
            if (cChar == FORMAT_END) {
                index++;
                continue;
            }

            // 匹配开始
            if (cChar == FORMAT_BEGIN && cChar != nChar) {
                number = nChar - 48;
            } else {
                charArr[length] = cChar;
                length++;
            }
            index++;
        }

        return new String(charArr, 0, length);
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

    /**
     * 驼峰转下划线
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static String caseToUnderline(String str) {
        int len = str.length();
        char[] names = new char[len * 2];
        names[0] = str.charAt(0);

        // 需要转换的数量
        int j = 0;
        for (int i = 1; i < len; i++) {
            char curr = str.charAt(i);

            // 判断前一个是否小写
            boolean provLower = Character.isLowerCase(str.charAt(i + j - 1));
            // 当前是大写，前一个是小写
            if (Character.isUpperCase(curr) && provLower) {
                names[i + j] = CHAR_UNDERLINE;
                j++;
            }
            names[i + j] = Character.toLowerCase(curr);
        }

        // 不需要转换
        if (j == 0) {
            return str;
        }

        return new String(names, 0, len + j);
    }
}
