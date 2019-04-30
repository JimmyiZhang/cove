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

    protected BaseEnumDeserializer() {
        super(BaseEnum.class);
    }

    @Override
    public BaseEnum getEmptyValue(DeserializationContext context) throws JsonMappingException {
        return null;
    }

    @Override
    public BaseEnum deserialize(JsonParser p, DeserializationContext context) throws IOException {
        BaseEnum one = null;

        int enumValue = p.getValueAsInt();
        BaseEnum[] enums = (BaseEnum[]) this.handledType().getEnumConstants();
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getValue() == enumValue) {
                one = enums[i];
                break;
            }
        }

        return one;
    }

    @Override
    public BaseEnum deserializeWithType(JsonParser p, DeserializationContext context, TypeDeserializer typeDeserializer) throws IOException {
        return this.deserialize(p, context);
    }
}
