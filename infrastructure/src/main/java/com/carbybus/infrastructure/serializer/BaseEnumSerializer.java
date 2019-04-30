package com.carbybus.infrastructure.serializer;

import com.carbybus.infrastructure.component.BaseEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 枚举类型转换器
 * @author jimmy.zhang
 * @date 2019-04-29
 */
public class BaseEnumSerializer extends StdSerializer<BaseEnum> {
    public static final BaseEnumSerializer instance = new BaseEnumSerializer();

    protected BaseEnumSerializer() {
        super(BaseEnum.class);
    }

    @Override
    public boolean isEmpty(SerializerProvider prov, BaseEnum value) {
        return value == null;
    }

    @Override
    public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(value.getValue());
    }

    @Override
    public void serializeWithType(BaseEnum value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.VALUE_STRING));
        this.serialize(value, g, provider);
        typeSer.writeTypeSuffix(g, typeIdDef);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        return this.createSchemaNode("BaseEnum", true);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        this.visitStringFormat(visitor, typeHint);
    }
}
