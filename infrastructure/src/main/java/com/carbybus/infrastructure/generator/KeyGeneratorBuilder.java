package com.carbybus.infrastructure.generator;

/**
 * 主键构造器
 * 用来生成一个主键或多个主键
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
public class KeyGeneratorBuilder {
    public static final KeyGeneratorBuilder INSTANCE = new KeyGeneratorBuilder();

    private KeyGenerator generator;

    private KeyGeneratorBuilder() {
        this.generator = new SnowflakeKeyGenerator(0);
    }

    public long build() {
        return this.generator.generateKey();
    }
}
