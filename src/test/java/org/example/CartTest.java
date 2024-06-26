package org.example;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.example.cart_components.Cart;
import org.example.cart_components.CartId;
import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.discount.repository.DiscountRepository;
import org.example.coupons.discount.type.DiscountType;
import org.example.coupons.discount.type.FlatPercentDiscount;
import org.example.coupons.discount.type.FreeTransportDiscount;
import org.example.coupons.manager.CouponManager;
import org.example.coupons.manager.CouponManagerImpl;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

class CartTest {
    private static final String NON_EXISTING_PRODUCT_NAME = "nonExistingProductName";
    private static final CartId EXAMPLE_CART_ID = new CartId("16697fa0-585f-43fc-a838-07f79186f591");
    ProductDefinition productDefinition = sampleProduct();
    //public static final DiscountDefinition FLAT_10_PERCENT_DISCOUNT_DEFINITION = new FlatPercentDiscount("abc", 10.0);

    public static DiscountDefinition FLAT_10_PERCENT = new DiscountDefinition("code", Map.of(
            DiscountType.Product, new FlatPercentDiscount(BigDecimal.TEN)
    ));

    public static DiscountDefinition FREE_TRANSPORT = new DiscountDefinition("aaa", Map.of(
            DiscountType.Transport, new FreeTransportDiscount("aaa", 10.00)
    ));

    CouponManager couponManager;

    @BeforeEach
    void setUp() {
        DiscountRepository discountRepository = new DiscountRepository();

        couponManager = new CouponManagerImpl(discountRepository);
    }

    //region addItem method test
    @Test
    void givenAnEmptyCart_whenAddedOneItem_thenCheckingIfDoesNotThrowAnyException() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        //when then
        Assertions.assertThatCode(() -> cart.addItem(productDefinition, 1)).doesNotThrowAnyException();
    }

    @Test
    void givenAnEmptyCart_whenAddedOneItem_thenCheckingIfIsItTrueThatItemWasAdded() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        //when
        cart.addItem(productDefinition, 1);

        //then
        Assertions.assertThat(cart.has("Butter")).isTrue();
    }

    @Test
    void givenAnEmptyCart_whenNotModifyingContent_thenCheckingIfItIsFalseThatItemDoesNotExistInContent() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        //when-then
        Assertions.assertThat(cart.has("Butter")).isFalse();
    }

    @Test
    void givenAnEmptyCart_whenAddingOneItemWith99Quantity_thenCheckingIfTrueThatCartHasAddedItem() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        //when
        cart.addItem(productDefinition, 99);

        //then
        Assertions.assertThat(cart.has("Butter")).isTrue();

    }

    @Test
    void givenAnEmptyCart_whenAddingItemWhit0Quantity_thenCheckingIfItIsFalseThatItemIsInCart() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        //when
        cart.addItem(productDefinition, 0);

        //then
        Assertions.assertThat(cart.has("Butter")).isFalse();

    }

    @Test
    void givenAnEmptyCart_thenAddingItemWithNullProductNameAndCheckingIfExceptionIsThrown() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        //when-then
        Assertions.assertThatThrownBy(() -> cart.addItem(null, 1)).hasMessage("ProductDefinition cannot be null");

    }

    @Test
    void givenAnEmptyCart_whenAddingItemWithNegativeQuantity_thenCheckingIfExceptionIsThrown() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        //when
        ThrowableAssert.ThrowingCallable addItem = () -> cart.addItem(productDefinition, -2);

        //then
        Assertions.assertThatThrownBy(addItem).hasMessage("You cannot add negative value of productDefinition");

    }

    @Test
    void givenACartWithOneItem_whenAddedTheSameItem_thenQuantityIsASumOfItems() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);
        cart.addItem(productDefinition, 1);

        //when
        cart.addItem(productDefinition, 1);

        //then
        Assertions.assertThat(cart.has(productDefinition.getName().getValue())).isTrue();
        Assertions.assertThat(cart.quantityOf(productDefinition.getName().getValue())).isEqualTo(2);
    }

    @Test
    void givenAnEmptyCart_whenAddingItemWithOneQuantity_thenAddingItemWith2QuantityAndCheckingIfItemIsInContentAndQuantityIsSummed() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        //when
        cart.addItem(productDefinition, 1);

        //then
        cart.addItem(productDefinition, 2);

        Assertions.assertThat(cart.has(productDefinition.getName().getValue())).isTrue();
        Assertions.assertThat(cart.quantityOf(productDefinition.getName().getValue())).isEqualTo(3);
    }

    @Test
    void givenAnEmptyCart_whenAddingTwoSameItemsTwoTimesWithDifferentQuantity_ThenCheckingIfContentHasItemAndIfQuantityIsSummedUp() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        //when
        cart.addItem(productDefinition, 2);
        cart.addItem(productDefinition, 1);

        //then
        Assertions.assertThat(cart.has(productDefinition.getName().getValue())).isTrue();
        Assertions.assertThat(cart.quantityOf(productDefinition.getName().getValue())).isEqualTo(3);
    }

    @Test
    void givenAnEmptyCart_thenCheckingCartContentIfNotAddedItemQuantityIsZero() {
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(0);
    }

    @Test
    void giveACartWithOneItem_whenRemovingItem_checkingIfItemWasRemovedWithQuantity() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);
        cart.addItem(productDefinition, 1);

        //when
        cart.removeItem("Butter");

        //then
        Assertions.assertThat(cart.has("Butter")).isFalse();
        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(0);
    }
    //endregion

    //region removeItem method test
    @Test
    void givenCartWithOneItem_whenRemovingOneItem_thenCartHasItemIsFalse() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        cart.addItem(productDefinition, 1);

        //when
        cart.removeItem("Butter");

        //then
        Assertions.assertThat(cart.has("Butter")).isFalse();
        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(0);
    }

    @Test
    void givenCartWithTwoItems_whenRemovingOneItem_thenCartHasItemIsTrue() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        cart.addItem(productDefinition, 3);

        //when
        cart.removeItem("Butter");

        //then
        Assertions.assertThat(cart.has("Butter")).isFalse();
        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(0);
    }
    //endregion

    //region removeQuantity method tests
    @Test
    void givenCartWithTwoItem_whenRemovingQuantityOfItem_thenLeftItemWithOneQuantity() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        cart.addItem(productDefinition, 2);

        //when
        cart.removeQuantity(productDefinition.getName().getValue(), 1);

        Assertions.assertThat(cart.quantityOf(productDefinition.getName().getValue())).isEqualTo(1);

    }

    @Test
    void givenCartWithTwoItem_whenRemovingNegativeValueOfQuantity_thenThrowException() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);
        cart.addItem(productDefinition, 2);

        //when
        ThrowableAssert.ThrowingCallable removeQuantity = () -> cart.removeQuantity("Butter", -3);

        Assertions.assertThatThrownBy(removeQuantity).hasMessage("You cannot remove negative value of products");

    }

    @Test
    void givenCartWithMultipleAddedItems_whenRemovingQuantityMultipleTimes_thenCheckingIfResultIsCorrect() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);
        cart.addItem(productDefinition, 2);
        cart.addItem(productDefinition, 2);
        cart.addItem(productDefinition, 2);
        cart.addItem(productDefinition, 2);
        //when
        cart.removeQuantity("butter", 1);
        cart.removeQuantity("butter", 1);
        cart.removeQuantity("butter", 1);
        cart.removeQuantity("butter", 1);

        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(4);

    }

    @Test
    void givenAnEmptyCart_whenRemoveOneItemQuantity_thenThrowingException() {
        //given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        //when then
        Assertions.assertThatThrownBy(() -> cart.removeQuantity("Butter", 1)).hasMessage("You cannot remove quantity of productDefinition that is not in your cart");
    }

    @Test
    void givenCartWithOneItem_thenRemovingItemQuantityWithNullProductNameAndCheckingIfExceptionIsThrown() {
        // given
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        // when
        ThrowableAssert.ThrowingCallable action = () -> cart.removeQuantity(NON_EXISTING_PRODUCT_NAME, 1);

        // then
        Assertions.assertThatThrownBy(action)
                .hasMessage("You cannot remove quantity of productDefinition that is not in your cart");
    }

    //endregion - -

    //region overallSum method tests
    @Test
    void creatingOverallSum() {
        Cart cart = new Cart(EXAMPLE_CART_ID, MockCouponManager.INSTANCE);

        cart.addItem(productDefinition, 1000);

        Assertions.assertThat(cart.overallSum()).isEqualTo(Money.of(BigDecimal.valueOf(2300.0), Currency.PLN));
    }
    //endregion

    //region applyDiscountForCode method tests
    @Test
    void testingFlat_Percent_Discount() {
        // given
        Cart cart = new Cart(EXAMPLE_CART_ID, couponManager);
        couponManager.addDiscount(FLAT_10_PERCENT);
        cart.addItem(sampleProduct(), 1000);

        // when
        cart.applyDiscountCode(FLAT_10_PERCENT.getCode());

        // then
        Assertions.assertThat(cart.overallSum())
                .isEqualByComparingTo(Money.of(BigDecimal.valueOf(2070.00), Currency.PLN));
    }

    @Test
    void testingFree_Transport_Discount() {
        // given
        Cart cart = new Cart(EXAMPLE_CART_ID, couponManager);
        couponManager.addDiscount(FREE_TRANSPORT);
        cart.addItem(sampleProduct(), 1000);

        // when
        cart.applyDiscountCode(FREE_TRANSPORT.getCode());

        // then
        Assertions.assertThat(cart.overallSum())
                .isEqualByComparingTo(Money.of(BigDecimal.valueOf(2300.00), Currency.PLN));
    }

    private static ProductDefinition sampleProduct() {
        return ProductDefinition.of("Butter", Money.of(BigDecimal.valueOf(2.3), Currency.PLN));
    }

    //endregion
    @Test
    void removingDiscountFromCart() {
        // given
        Cart cart = new Cart(EXAMPLE_CART_ID, couponManager);
        couponManager.addDiscount(FLAT_10_PERCENT);
        couponManager.addDiscount(FREE_TRANSPORT);
        cart.addItem(sampleProduct(), 1000);
        cart.applyDiscountCode(FLAT_10_PERCENT.getCode());
        cart.applyDiscountCode(FREE_TRANSPORT.getCode());

        // when
        cart.removeDiscount("code");

        // then
        Assertions.assertThat(cart.overallSum())
                .isEqualByComparingTo(Money.of(BigDecimal.valueOf(2300.00), Currency.PLN));
    }

    @Test
    void addingToCartByCartId() {
        Cart cart = new Cart(EXAMPLE_CART_ID, couponManager);

        CartId cartId = cart.getCartId();

        Assertions.assertThat(cartId).isNotNull();
        System.out.println(cartId);
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
