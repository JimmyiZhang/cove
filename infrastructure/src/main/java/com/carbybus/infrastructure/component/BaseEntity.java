package com.carbybus.infrastructure.component;

/**
 * <p>
 * 基础实体
 * </p>
 * <p>
 * 仅包括主键定义
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
public interface BaseEntity<T> {
    /**
     * 获取id
     *
     * @author jimmy.zhang
     * @date 2019-03-29
     */
    T getId();
}
