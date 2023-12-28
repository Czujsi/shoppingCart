package org.example.coupons;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class CouponManagerTest {
    public static DiscountDefinition FREE_TRANSPORT = new DiscountDefinition("code", Map.of(
            DiscountType.Transport, new FreeTransportDiscount("aaa", 10.00)
    ));
    @Test
    void addDiscount() {
        DiscountRepository discountRepository = new DiscountRepository();

        CouponManagerImpl couponManager = new CouponManagerImpl(discountRepository);

        couponManager.addDiscount(FREE_TRANSPORT);

        Assertions.assertThat(discountRepository.exists("code")).isTrue();
    }

    @Test
    void removeDiscount() {
        // given
        DiscountRepository discountRepository = new DiscountRepository();
        CouponManagerImpl couponManager = new CouponManagerImpl(discountRepository);
        couponManager.addDiscount(FREE_TRANSPORT);

        // when
        couponManager.removeDiscount(FREE_TRANSPORT);

        // then
        Assertions.assertThat(discountRepository.exists("code")).isFalse();
    }
}
