package com.carbybus.infrastructure.json;

import com.carbybus.infrastructure.component.BaseEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;
import java.lang.reflect.Field;

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
    public BaseEnum deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        String currentName = jp.currentName();
        Object currentValue = jp.getCurrentValue();

        BaseEnum one = null;
        int enumValue = jp.getValueAsInt();

        Field[] fields = currentValue.getClass().getDeclaredFields();
        // 默认有field
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(currentName)) {
                Object[] enums = fields[i].getType().getEnumConstants();
                // 默认有enum
                for (int j = 0; j < enums.length; j++) {
                    BaseEnum origin = (BaseEnum) enums[j];
                    if (origin.getValue() == enumValue) {
                        one = origin;
                        break;
                    }
                }
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
