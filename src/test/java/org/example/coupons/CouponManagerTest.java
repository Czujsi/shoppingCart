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
        DiscountInMemoryRepository discountInMemoryRepository = new DiscountInMemoryRepository();

        CouponManagerImpl couponManager = new CouponManagerImpl(discountInMemoryRepository);

        couponManager.addDiscount(FREE_TRANSPORT);

        Assertions.assertThat(discountInMemoryRepository.exists("code")).isTrue();
    }

    @Test
    void removeDiscount() {
        // given
        DiscountInMemoryRepository discountInMemoryRepository = new DiscountInMemoryRepository();
        CouponManagerImpl couponManager = new CouponManagerImpl(discountInMemoryRepository);
        couponManager.addDiscount(FREE_TRANSPORT);

        // when
        couponManager.removeDiscount(FREE_TRANSPORT);

        // then
        Assertions.assertThat(discountInMemoryRepository.exists("code")).isFalse();
    }
}
