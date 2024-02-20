package org.example.coupons.discount.repository;

import org.assertj.core.api.Assertions;
import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.discount.type.DiscountType;
import org.example.coupons.discount.type.FreeTransportDiscount;
import org.junit.jupiter.api.Test;

import java.util.Map;

class DiscountRepositoryTest {
    public static DiscountDefinition FREE_TRANSPORT = new DiscountDefinition("code", Map.of(
            DiscountType.Transport, new FreeTransportDiscount("aaa", 10.00)
    ));

    @Test
    void CheckingIfSaveMethodWorksAsIntended() {
        DiscountRepository discountRepository = new DiscountRepository();

        discountRepository.save(FREE_TRANSPORT);

        Assertions.assertThat(discountRepository.exists("code")).isTrue();
    }

    @Test
    void CheckingIfDeleteMethodWorksAsIntended() {
        DiscountRepository discountRepository = new DiscountRepository();
        discountRepository.save(FREE_TRANSPORT);

        discountRepository.delete("code");

        Assertions.assertThat(discountRepository.exists("code")).isFalse();
    }

    @Test
    void CheckingIfGetMethodWorksAsIntended() {
        DiscountRepository discountRepository = new DiscountRepository();
        discountRepository.save(FREE_TRANSPORT);

        Assertions.assertThat(discountRepository.get("code")).isEqualTo(FREE_TRANSPORT);
    }
}