package org.example.product.components;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.config.deserializers.PriceJacksonDeserializer;
import org.example.currency_exchange_money.Money;

@ToString
@EqualsAndHashCode
@JsonDeserialize(using = PriceJacksonDeserializer.class)
public class Price {
    public Money productPrice;

    public Price(Money productPrice) {
        this.productPrice = productPrice;
    }

}
