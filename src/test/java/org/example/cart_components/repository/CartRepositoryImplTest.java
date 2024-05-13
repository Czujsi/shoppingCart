package org.example.cart_components.repository;

import org.assertj.core.api.Assertions;
import org.example.account.UserId;
import org.example.cart_components.Cart;
import org.example.cart_components.CartId;
import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.discount.type.DiscountType;
import org.example.coupons.manager.CouponManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CartRepositoryImplTest {
    private final static UserId EXAMPLE_USER_ID = new UserId("bea3ccd9-108d-42b7-9af7-45dbcfcebfc3");
    private static final CartId EXAMPLE_CART_ID = new CartId("16697fa0-585f-43fc-a838-07f79186f591");

    @Test
    void checkingIfMethodSaveWorksAsIntended() {
        CartRepositoryImpl cartRepository = new CartRepositoryImpl();

        cartRepository.save(EXAMPLE_USER_ID, new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE));

        Assertions.assertThat(cartRepository.exists(new UserId("bea3ccd9-108d-42b7-9af7-45dbcfcebfc3"))).isTrue();
    }

    @Test
    void checkingIfMethodDeleteWorksAsIntended() {
        CartRepositoryImpl cartRepository = new CartRepositoryImpl();

        cartRepository.delete(EXAMPLE_USER_ID);

        Assertions.assertThat(cartRepository.exists(new UserId("bea3ccd9-108d-42b7-9af7-45dbcfcebfc3"))).isFalse();
    }

    @Test
    void checkingIfMethodGetWorksAsIntended() {
        CartRepositoryImpl cartRepository = new CartRepositoryImpl();

        Assertions.assertThat(cartRepository.get(EXAMPLE_USER_ID)).isEmpty();
        Assertions.assertThat(cartRepository.get(EXAMPLE_USER_ID)).isNotNull();
        Assertions.assertThat(cartRepository.get(EXAMPLE_USER_ID)).isEqualTo(new ArrayList<Cart>());
    }

    //region MockCouponManager
    static class MockCouponManager implements CouponManager {
        public final static CouponManager INSTANCE = new MockCouponManager();

        @Override
        public boolean checkDiscountCode(String code) {
            return false;
        }

        @Override
        public DiscountDefinition getCouponForCode(String code) {
            return null;
        }

        @Override
        public void addDiscount(DiscountDefinition discountDefinition) {

        }

        @Override
        public void removeDiscount(String code) {

        }

        @Override
        public DiscountType[] getDiscountTypes() {
            return new DiscountType[0];
        }
    }
    //endregion
}