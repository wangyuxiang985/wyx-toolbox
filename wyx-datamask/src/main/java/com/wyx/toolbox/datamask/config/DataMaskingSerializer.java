package com.wyx.toolbox.datamask.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * 自定义Serializer
 * @author wangyu
 */
public final class DataMaskingSerializer extends StdScalarSerializer<Object> {
    private final DataMaskingOperation operation;

    public DataMaskingSerializer() {
        super(String.class, false);
        this.operation = null;
    }

    public DataMaskingSerializer(DataMaskingOperation operation) {
        super(String.class, false);
        this.operation = operation;
    }


    @Override
    public boolean isEmpty(SerializerProvider prov, Object value) {
        String str = (String)value;
        return str.isEmpty();
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (Objects.isNull(operation)) {
            String content = DataMaskingFunc.ALL_MASK.operation().mask((String) value, null);
            gen.writeString(content);
        } else {
            String content = operation.mask((String) value, null);
            gen.writeString(content);
        }
    }

    @Override
    public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, provider);
    }

    public JsonNode getSchema(SerializerProvider provider) {
        return this.createSchemaNode("string", true);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        this.visitStringFormat(visitor, typeHint);
    }
}
