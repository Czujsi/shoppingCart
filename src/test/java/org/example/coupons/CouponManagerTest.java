package org.example.coupons;

import org.assertj.core.api.Assertions;
import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.discount.repository.DiscountRepository;
import org.example.coupons.discount.type.DiscountType;
import org.example.coupons.discount.type.FreeTransportDiscount;
import org.example.coupons.manager.CouponManagerImpl;
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
        couponManager.removeDiscount("code");

        // then
        Assertions.assertThat(discountRepository.exists("code")).isFalse();
    }
}
