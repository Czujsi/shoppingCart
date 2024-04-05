package org.example.config.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.product.history.ProductHistory;

import java.io.IOException;

public class ProductHistoryJacksonSerializer extends JsonSerializer<ProductHistory> {
    @Override
    public void serialize(ProductHistory productHistory, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeString(productHistory.toString());
    }
}
