import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.*;
import java.util.Set;


class Scratch {
    String sampleCsv = """
            date,original_currency,target_currency,rate
            2020-04-03,PLN,USD,0.46
            """;

    enum Currency {
        PLN, USD
        // Tutaj mamy wewnętrzną klasę enum, bez parametrów, która posiada nazwy walut na jakich możemy operować
    }

    public static void main(String[] args) {
        Converter converter = new Converter(Set.of(new ExchangeRate(LocalDate.of(2020, Month.APRIL, 3), Set.of(new ExchangeRate.SingleRate(Currency.PLN, Currency.USD, 0.46)))));

        Money price = new Money(1.3, Currency.PLN);
        ZonedDateTime date = ZonedDateTime.of(LocalDate.of(2020, Month.APRIL, 3), LocalTime.of(12, 40), ZoneOffset.UTC);
        Money convertedMoney = converter.convert(price, Currency.USD, date);

    }
}

@AllArgsConstructor
@ToString
class Converter {
    private final Set<ExchangeRate> exchangeRates;

    public Money convert(Money price, Scratch.Currency currency, ZonedDateTime date) {
        return exchangeRates.stream().filter(exchangeRate -> exchangeRate.forDate(date)).map(exchangeRate -> exchangeRate.exchange(price, currency)).toList().get(0);

    }
}

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
class ExchangeRate {
    @EqualsAndHashCode.Include
    private final LocalDate exchangeDate;

    //originalCurrency, targetCurrency, rate
    private final Set<SingleRate> exchangeRates;

    public boolean forDate(ZonedDateTime date) {
        return date.toLocalDate().isEqual(exchangeDate);
    }

    public Money exchange(Money price, Scratch.Currency targetCurrency) {

        return exchangeRates.stream().filter(singleRate -> singleRate.getOriginalCurrency().equals(price.getCurrency()))
                .filter(singleRate -> singleRate.getTargetCurrency().equals(targetCurrency))
                .map(singleRate -> singleRate.getRate() * price.getAmount()).map(newAmount -> new Money(newAmount, targetCurrency))
                .toList().get(0);
    }

    @Getter
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @AllArgsConstructor
    protected final static class SingleRate {
        @EqualsAndHashCode.Include
        private final Scratch.Currency originalCurrency;
        @EqualsAndHashCode.Include
        private final Scratch.Currency targetCurrency;
        private final double rate;

    }

}

@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
class Money {
    private final double amount;
    private final Scratch.Currency currency;

}