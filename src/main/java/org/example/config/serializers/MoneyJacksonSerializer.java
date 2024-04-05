package org.example.config.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.currency_exchange_money.Money;

import java.io.IOException;

public class MoneyJacksonSerializer extends JsonSerializer<Money> {
    @Override
    public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(money.format());
    }
}
