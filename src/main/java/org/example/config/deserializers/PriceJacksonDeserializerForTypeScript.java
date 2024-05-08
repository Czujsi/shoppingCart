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

public class PriceJacksonDeserializerForTypeScript extends JsonDeserializer<Price> {
    @Override
    public Price deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String textAmount = node.get("amount").asText();
        String textCurrency = node.get("currency").asText();

        BigDecimal amount = new BigDecimal(textAmount);
        Currency currency = Currency.valueOf(textCurrency);


        Money productPrice = Money.of(amount, currency);


        return new Price(productPrice);
    }
}
