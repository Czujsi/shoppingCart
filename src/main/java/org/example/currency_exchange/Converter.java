package org.example.currency_exchange;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.Set;

@AllArgsConstructor
@ToString
public class Converter {

    private final Set<ExchangeRate> exchangeRates;

    // tworzymy tutaj prywatny finalny Set ExchangeRate o nazwie exchangeRates
    public Money convert(Money price, Currency currency, ZonedDateTime date) {
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

