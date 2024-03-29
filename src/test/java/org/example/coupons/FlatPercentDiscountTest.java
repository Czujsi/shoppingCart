package org.example.coupons;

import org.assertj.core.api.Assertions;
import org.example.coupons.discount.type.FlatPercentDiscount;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class FlatPercentDiscountTest {
    @Test
    void flatTenPercent() {
        // given
        FlatPercentDiscount discount = new FlatPercentDiscount(BigDecimal.TEN);

        // when
        Money result = discount.discount(Money.of(BigDecimal.TEN, Currency.PLN));

        // then
        Assertions.assertThat(result)
                .isEqualByComparingTo(Money.of(BigDecimal.valueOf(9), Currency.PLN));
    }

}