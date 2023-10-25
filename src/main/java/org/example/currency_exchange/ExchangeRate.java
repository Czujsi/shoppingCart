package org.example.currency_exchange;

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
    enum Currency {
        PLN, USD
        // Tutaj mamy wewnętrzną klasę enum, bez parametrów, która posiada nazwy walut na jakich możemy operować
    }

    @EqualsAndHashCode.Include
    private final LocalDate exchangeDate;

    //originalCurrency, targetCurrency, rate
    private final Set<SingleRate> exchangeRates;
    //tutaj jest stworzony Set single rate

    public boolean forDate(ZonedDateTime date) {
        return date.toLocalDate().isEqual(exchangeDate);
    }
    //sprawdzamy czy dana data to dzisiejsza lokalna data

    public Money exchange(Money price, Currency targetCurrency) {
        //tutaj mamy przyjecie ilości pieniędzy i przyjecie waluty na którą chcemy wymieniać pieniądze

        return exchangeRates.stream().filter(singleRate -> singleRate.getOriginalCurrency().equals(price.getCurrency()))
                //stremujemy exchange rate i przepuszczamy przez filtr biorąc z single rate naszą oryginalną walutę i sprawdzamy walute???
                .filter(singleRate -> singleRate.getTargetCurrency().equals(targetCurrency))
                //tutaj filtrujemy z single rate naszą walutę docelową i sprawdzamy co to za waluta ???
                .map(singleRate -> singleRate.getRate() * price.getAmount()).map(newAmount -> new Money(newAmount, targetCurrency))
                //mapujemy do tego, że single rate to przelicznik mnożony przez ilość pieniędzy z czego wychodzą nam jakby nowe pieniądze z nową ilością w walucie docelowej
                .toList().get(0);
        //na koniec wrzucamy to do listy i bierzemy pierwszą ilość
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
        //tworzymy klasę single rate ze zmiennymi oryginalej waluty, waluty na, którą wymieniamy i przelicznikiem

    }
}
