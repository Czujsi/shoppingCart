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
        Converter converter = new Converter(Set.of(new ExchangeRate(LocalDate.of(2020, Month.APRIL, 3),
                Set.of(new ExchangeRate.SingleRate(Currency.PLN, Currency.USD, 0.46)))));
        //tutaj tworzymy nowy Converter do wymiany walut, którego parametry to:
        //tworzymy set(używamy seta aby zapobiec duplikowaniu) nowych ExchangeRate na dany dzień
        //tworzymy set nowych SingleRate(przeliczników z jednej waluty na drugą czyli z PLN na USD za pomocą mnożnika rate)

        Money price = new Money(1.3, Currency.PLN);
        //tworzymy klasę Money price z parametrami amount oraz Currency, która służy nam do wyboru danej waluty i jej ilości z jakiej chcemy przeliczać
        ZonedDateTime date = ZonedDateTime.of(LocalDate.of(2020, Month.APRIL, 3), LocalTime.of(12, 40), ZoneOffset.UTC);
        //tworzymy datę oraz czas, którą ustawiamy na konkretny dzień i godzinę ale ustawiamy to również na czas UTC
        Money convertedMoney = converter.convert(price, Currency.USD, date);

    }
}

@AllArgsConstructor
@ToString
class Converter {
    private final Set<ExchangeRate> exchangeRates;
    // tworzymy tutaj prywatny finalny Set ExchangeRate o nazwie exchangeRates
    public Money convert(Money price, Scratch.Currency currency, ZonedDateTime date) {
        //tutaj mamy konstruktor Money (ale nie wiem skąd się wział w sennsie skąd konstruktor z taką ilościa parametrów skoro klasa money ma tylko dwa)
        //czym dokładnie jest convert? widzę że to metoda którą mamy wyżej i przyjmuje trzy parametry ale jak ona jest tutaj połączona z konstruktorem
        return exchangeRates.stream().filter(exchangeRate -> exchangeRate.forDate(date))
                //zwracamy exchangeRates(wartości przeliczników na dany dzień), które są streamowane przez filtr na daną datę
                .map(exchangeRate -> exchangeRate.exchange(price, currency))
                //mapowane z exchangeRate na exchangeRate.exchange czyli do metody która pobiera ilość pieniędzy zamienia ją na przelicznik
                .toList().get(0);
                //cały stream bierzemy do listy, która może mieć od 0 do nieskończoności miejsc i bierzemy z niej pierwszą pozycję
                // ponieważ numeracja zaczyna się od zera więc zero jest pierwszą pozycją
                //i pierwsza pozycja jest stawką wymiany na dany dzień

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
    //tutaj jest stworzony Set single rate

    public boolean forDate(ZonedDateTime date) {
        return date.toLocalDate().isEqual(exchangeDate);
    }
    //sprawdzamy czy dana data to dzisiejsza lokalna data

    public Money exchange(Money price, Scratch.Currency targetCurrency) {
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
        private final Scratch.Currency originalCurrency;
        @EqualsAndHashCode.Include
        private final Scratch.Currency targetCurrency;
        private final double rate;
        //tworzymy klasę single rate ze zmiennymi oryginalej waluty, waluty na, którą wymieniamy i przelicznikiem

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