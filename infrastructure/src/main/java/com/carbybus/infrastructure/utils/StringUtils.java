package com.carbybus.infrastructure.utils;

/**
 * 字符串帮助类
 *
 * @author jimmy.zhang
 * @date 2019-03-08
 */
public class StringUtils {
    /**
     * 私有构造器
     */
    private StringUtils() {
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

        return source.substring(startIndex, length);
    }
}
