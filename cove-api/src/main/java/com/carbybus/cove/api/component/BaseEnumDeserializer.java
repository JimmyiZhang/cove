package com.carbybus.cove.api.component;

import com.carbybus.infrastructure.component.BaseEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;

/**
 * 枚举类型转换器
 *
 * @author jimmy.zhang
 * @date 2019-04-29
 */
public class BaseEnumDeserializer extends StdDeserializer<BaseEnum> {
    public static final BaseEnumDeserializer instance = new BaseEnumDeserializer();

    public BaseEnumDeserializer() {
        super(BaseEnum.class);
    }

    @Override
    public Object getEmptyValue(DeserializationContext context) throws JsonMappingException {
        return null;
    }

    @Override
    public BaseEnum deserialize(JsonParser p, DeserializationContext context) throws IOException {
        return null;
        // p.getValueAsInt()
    }

    @Override
    public BaseEnum deserializeWithType(JsonParser p, DeserializationContext context, TypeDeserializer typeDeserializer) throws IOException {
        return this.deserialize(p, context);
    }
}
