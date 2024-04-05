package org.example.config.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.components.Price;

import java.io.IOException;
import java.math.BigDecimal;

public class PriceJacksonDeserializer extends JsonDeserializer<Price> {

    @Override
    public Price deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String textPrice = node.textValue();

        String[] parts = textPrice.split(" ");

        BigDecimal amount = new BigDecimal(parts[0]);
        Currency currency = Currency.valueOf(parts[1]);


        Money productPrice = Money.of(amount, currency);


        return new Price(productPrice);
    }
}
