package org.example.currency_exchange;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
public class Money {
    private double amount;
    private ExchangeRate.Currency currency;
}
