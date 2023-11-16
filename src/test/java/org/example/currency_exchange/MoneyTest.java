package org.example.currency_exchange;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class MoneyTest {
    @Test
    void shouldThrowExceptionWhenAddingDifferentCurrencies() {
        Money a = Money.of(BigDecimal.ONE, Currency.PLN);
        Money b = Money.of(BigDecimal.ONE, Currency.USD);

        Assertions.assertThatThrownBy(() -> a.add(b)).hasMessage("You cannot add different currencies");
    }

    @Test
    void shouldThrowExceptionWhenSubtractingDifferentCurrencies() {
        Money a = Money.of(BigDecimal.ONE, Currency.PLN);
        Money b = Money.of(BigDecimal.ONE, Currency.USD);

        Assertions.assertThatThrownBy(() -> a.subtract(b)).hasMessage("You cannot add different currencies");
    }
}