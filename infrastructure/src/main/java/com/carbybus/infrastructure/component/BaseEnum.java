package com.carbybus.infrastructure.component;

import com.carbybus.infrastructure.serializer.BaseEnumDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 通用枚举
 *
 * @author jimmy.zhang
 * @date 2019-04-29
 */
@JsonDeserialize(using = BaseEnumDeserializer.class)
public interface BaseEnum {
    Integer getValue();
}
