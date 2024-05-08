package org.example.currency_exchange_money;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.Set;

@AllArgsConstructor
@ToString
public class Converter {
    private final Set<ExchangeRate> exchangeRates;

    public Money convert(Money price, Currency currency, ZonedDateTime date) {
        return exchangeRates.stream().filter(exchangeRate -> exchangeRate.forDate(date))
                .map(exchangeRate -> exchangeRate.exchange(price, currency))
                .toList().getFirst();
    }
}

