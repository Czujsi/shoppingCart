package org.example;

import org.example.account.UserId;
import org.example.cart_components.Cart;
import org.example.cart_components.CartId;
import org.example.cart_components.manager.CartManager;
import org.example.cart_components.manager.CartManagerImpl;
import org.example.cart_components.repository.CartRepository;
import org.example.cart_components.repository.CartRepositoryImpl;
import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.discount.type.DiscountType;
import org.example.coupons.manager.CouponManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartManagerImplTest {
    private final static UserId USER_ID = new UserId("1c821945-b6c0-4ce1-b073-dfc6c6da3694");
    private final static UserId USER_ID_2 = new UserId("8be75339-b73f-4521-b2ec-c67dc7aeb826");
    private static final CartId EXAMPLE_CART_ID = new CartId("16697fa0-585f-43fc-a838-07f79186f591");

    private CartRepository<UserId, Cart> repository;
    private CartManager manager;

    @BeforeEach
    void setUp() {
        repository = new CartRepositoryImpl();
        manager = new CartManagerImpl(repository);
    }

    @Test
    void checkingIfAddCartMethodWorksProperly() {
        manager.addCart(USER_ID, new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE));

        assertThat(repository.exists(USER_ID)).isTrue();
    }

    @Test
    void checkingIfAddCartMethodWorksProperly2() {
        assertThat(repository.exists(USER_ID_2)).isFalse();
    }

    @Test
    void checkingIfDeleteCartMethodWorksProperly() {
        manager.addCart(USER_ID, new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE));

        manager.deleteCart(USER_ID);

        assertThat(repository.exists(USER_ID)).isFalse();
    }

//    private Cart cartForUser(UserId userId) {
//        Cart cart = mock(Cart.class);
//        when(cart.getUserId()).thenReturn(userId);
//        return cart;
//    }

    //region MockCouponManager
    static class MockCouponManager implements CouponManager {
        public final static CouponManager INSTANCE = new CartTest.MockCouponManager();

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