package com.carbybus.infrastructure.component;

import com.carbybus.infrastructure.json.BaseEnumDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 通用枚举
 *
 * @author jimmy.zhang
 * @date 2019-04-29
 */
@JsonDeserialize(using = BaseEnumDeserializer.class)
public interface BaseEnum {
    /**
     * 获取枚举值
     *
     * @author jimmy.zhang
     * @date 2019-03-29
     */
    Integer getValue();
}
