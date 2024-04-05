package org.example.config.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.product.components.ProductId;

import java.io.IOException;

public class ProductIdJacksonSerializer extends JsonSerializer<ProductId> {

    @Override
    public void serialize(ProductId productId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(productId.getValue().toString());
    }
}
