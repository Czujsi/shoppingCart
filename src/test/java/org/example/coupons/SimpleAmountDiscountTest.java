package org.example.coupons;

import org.assertj.core.api.Assertions;
import org.example.coupons.discount.type.SimpleAmountDiscount;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.junit.jupiter.api.Test;

class SimpleAmountDiscountTest {
    @Test
    void subtract() {
        SimpleAmountDiscount simpleAmountDiscount = new SimpleAmountDiscount(Money.of(10, Currency.PLN));

        Money price = Money.of(20 , Currency.PLN);

        Money result = simpleAmountDiscount.discount(price);

        Assertions.assertThat(result).isEqualTo(Money.of(10, Currency.PLN));
    }

}