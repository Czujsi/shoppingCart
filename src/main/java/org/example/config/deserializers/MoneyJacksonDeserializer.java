package org.example.config.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;

import java.io.IOException;
import java.math.BigDecimal;

public class MoneyJacksonDeserializer extends JsonDeserializer<Money> {
    @Override
    public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String textPrice = node.get("price").asText();

        String[] parts = textPrice.split(" ");


        BigDecimal amount = new BigDecimal(parts[0]);
        Currency currency = Currency.valueOf(parts[1]);


        return Money.of(amount, currency);
    }
}
