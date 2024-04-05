package org.example.config.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.product.components.Name;

import java.io.IOException;

public class ProductNameJacksonSerializer extends JsonSerializer<Name> {

    @Override
    public void serialize(Name name, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(name.getValue());
    }
}
