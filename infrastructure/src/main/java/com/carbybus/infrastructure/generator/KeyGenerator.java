package com.carbybus.infrastructure.generator;

/**
 * 主键生成器
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
public interface KeyGenerator {
    String getType();

    long generateKey();
}
