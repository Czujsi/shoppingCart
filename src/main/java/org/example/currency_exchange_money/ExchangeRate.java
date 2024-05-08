package org.example.currency_exchange_money;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class ExchangeRate {

    @EqualsAndHashCode.Include
    private final LocalDate exchangeDate;

    private final Set<SingleRate> exchangeRates;

    public boolean forDate(ZonedDateTime date) {
        return date.toLocalDate().isEqual(exchangeDate);
    }

    public Money exchange(Money price, Currency targetCurrency) {
        return exchangeRates.stream().filter(singleRate -> singleRate.getOriginalCurrency().equals(price.getCurrency()))
                .filter(singleRate -> singleRate.getTargetCurrency().equals(targetCurrency))
                .map(singleRate -> singleRate.getRate() * price.getAmount().doubleValue()).map(newAmount -> Money.of(newAmount, targetCurrency))
                .toList().getFirst();
    }

    @Getter
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @AllArgsConstructor
    protected final static class SingleRate {
        @EqualsAndHashCode.Include
        private final Currency originalCurrency;
        @EqualsAndHashCode.Include
        private final Currency targetCurrency;
        private final double rate;
    }
}
