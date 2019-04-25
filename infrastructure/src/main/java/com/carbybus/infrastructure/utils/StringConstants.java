package com.carbybus.infrastructure.utils;

/**
 * 字符串常量类
 *
 * @author jimmy.zhang
 * @date 2019-03-08
 */
public class StringConstants {
    /**
     * 工具类使用私有构造器覆盖公共构造器，防止公共构造器被调用
     * Sonar Code smell Major squid:S1118
     */
    private StringConstants() {
    }

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 空字符串
     */
    public static final String RANDOM_EASY_WORD = "aAbBcCdDeEfFgGhHjJkKLmMnNpPqQrRsStTuUvVwWxXyYzZ23456789";
}
