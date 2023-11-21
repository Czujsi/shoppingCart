package org.example;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.example.coupons.*;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

class CartTest {
    private static final String NON_EXISTING_PRODUCT_NAME = "nonExistingProductName";
    Product product = sampleProduct(2.30);
    //public static final DiscountDefinition FLAT_10_PERCENT_DISCOUNT_DEFINITION = new FlatPercentDiscount("abc", 10.0);

    public static DiscountDefinition FLAT_10_PERCENT = new DiscountDefinition("code", Map.of(
            DiscountType.Product, new FlatPercentDiscount(BigDecimal.TEN)
    ));

    public static DiscountDefinition FREE_TRANSPORT = new DiscountDefinition("code", Map.of(
            DiscountType.Transport, new FreeTransportDiscount("aaa", 10.00)
    ));

    public static DiscountDefinition CART_AND_PRODUCT = new DiscountDefinition("code", Map.of(
            DiscountType.Product, new FlatPercentDiscount(BigDecimal.TEN),
            DiscountType.Cart, new SimpleAmountDiscount(Money.of(20.0, Currency.PLN))
    ));

    CouponManager couponManager;

    @BeforeEach
    void setUp() {
        DiscountInMemoryRepository discountInMemoryRepository = new DiscountInMemoryRepository();

        couponManager = new CouponManagerImpl(discountInMemoryRepository);
    }

    //region addItem method test
    @Test
    void givenAnEmptyCart_whenAddedOneItem_thenCheckingIfDoesNotThrowAnyException() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        //when then
        Assertions.assertThatCode(() -> cart.addItem(product, 1)).doesNotThrowAnyException();
    }

    @Test
    void givenAnEmptyCart_whenAddedOneItem_thenCheckingIfIsItTrueThatItemWasAdded() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        //when
        cart.addItem(product, 1);

        //then
        Assertions.assertThat(cart.has("Butter")).isTrue();

    }

    @Test
    void givenAnEmptyCart_whenNotModifyingContent_thenCheckingIfItIsFalseThatItemDoesNotExistInContent() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        //when-then
        Assertions.assertThat(cart.has("Butter")).isFalse();

    }

    @Test
    void givenAnEmptyCart_whenAddingOneItemWith99Quantity_thenCheckingIfTrueThatCartHasAddedItem() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        //when
        cart.addItem(product, 99);

        //then
        Assertions.assertThat(cart.has("Butter")).isTrue();

    }

    @Test
    void givenAnEmptyCart_whenAddingItemWhit0Quantity_thenCheckingIfItIsFalseThatItemIsInCart() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        //when
        cart.addItem(product, 0);

        //then
        Assertions.assertThat(cart.has("Butter")).isFalse();

    }

    @Test
    void givenAnEmptyCart_thenAddingItemWithNullProductNameAndCheckingIfExceptionIsThrown() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        //when-then
        Assertions.assertThatThrownBy(() -> cart.addItem(null, 1)).hasMessage("Product cannot be null");

    }

    @Test
    void givenAnEmptyCart_whenAddingItemWithNegativeQuantity_thenCheckingIfExceptionIsThrown() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        //when
        ThrowableAssert.ThrowingCallable addItem = () -> cart.addItem(product, -2);

        //then
        Assertions.assertThatThrownBy(addItem).hasMessage("You cannot add negative value of product");

    }

    @Test
    void givenACartWithOneItem_whenAddedTheSameItem_thenQuantityIsASumOfItems() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);
        cart.addItem(product, 1);

        //when
        cart.addItem(product, 1);

        //then
        Assertions.assertThat(cart.has(product.getProductName().getValue())).isTrue();
        Assertions.assertThat(cart.quantityOf(product.getProductName().getValue())).isEqualTo(2);
    }

    @Test
    void givenAnEmptyCart_whenAddingItemWithOneQuantity_thenAddingItemWith2QuantityAndCheckingIfItemIsInContentAndQuantityIsSummed() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        //when
        cart.addItem(product, 1);

        //then
        cart.addItem(product, 2);

        Assertions.assertThat(cart.has(product.getProductName().getValue())).isTrue();
        Assertions.assertThat(cart.quantityOf(product.getProductName().getValue())).isEqualTo(3);
    }

    @Test
    void givenAnEmptyCart_whenAddingTwoSameItemsTwoTimesWithDifferentQuantity_ThenCheckingIfContentHasItemAndIfQuantityIsSummedUp() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        //when
        cart.addItem(product, 2);
        cart.addItem(product, 1);

        //then
        Assertions.assertThat(cart.has(product.getProductName().getValue())).isTrue();
        Assertions.assertThat(cart.quantityOf(product.getProductName().getValue())).isEqualTo(3);
    }

    @Test
    void givenAnEmptyCart_thenCheckingCartContentIfNotAddedItemQuantityIsZero() {
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(0);
    }

    @Test
    void giveACartWithOneItem_whenRemovingItem_checkingIfItemWasRemovedWithQuantity() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);
        cart.addItem(product, 1);

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
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        cart.addItem(product, 1);

        //when
        cart.removeItem("Butter");

        //then
        Assertions.assertThat(cart.has("Butter")).isFalse();
        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(0);
    }

    @Test
    void givenCartWithTwoItems_whenRemovingOneItem_thenCartHasItemIsTrue() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        cart.addItem(product, 3);

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
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        cart.addItem(product, 2);

        //when
        cart.removeQuantity(product.getProductName().getValue(), 1);

        Assertions.assertThat(cart.quantityOf(product.getProductName().getValue())).isEqualTo(1);

    }

    @Test
    void givenCartWithTwoItem_whenRemovingNegativeValueOfQuantity_thenThrowException() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);
        cart.addItem(product, 2);

        //when
        ThrowableAssert.ThrowingCallable removeQuantity = () -> cart.removeQuantity("Butter", -3);

        Assertions.assertThatThrownBy(removeQuantity).hasMessage("You cannot remove negative value of products");

    }

    @Test
    void givenCartWithMultipleAddedItems_whenRemovingQuantityMultipleTimes_thenCheckingIfResultIsCorrect() {
        //given
        Cart cart = new Cart(MockCouponManager.INSTANCE);
        cart.addItem(product, 2);
        cart.addItem(product, 2);
        cart.addItem(product, 2);
        cart.addItem(product, 2);
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
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        //when then
        Assertions.assertThatThrownBy(() -> cart.removeQuantity("Butter", 1)).hasMessage("You cannot remove quantity of product that is not in your cart");
    }

    @Test
    void givenCartWithOneItem_thenRemovingItemQuantityWithNullProductNameAndCheckingIfExceptionIsThrown() {
        // given
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        // when
        ThrowableAssert.ThrowingCallable action = () -> cart.removeQuantity(NON_EXISTING_PRODUCT_NAME, 1);

        // then
        Assertions.assertThatThrownBy(action)
                .hasMessage("You cannot remove quantity of product that is not in your cart");
    }

    @Test
    void name() {
        Assertions.assertThat("abc").isEqualTo("abc");
    }
    //endregion - -

    //region overallSum method tests
    @Test
    void creatingOverallSum() {
        Cart cart = new Cart(MockCouponManager.INSTANCE);

        cart.addItem(product, 1000);

        Assertions.assertThat(cart.overallSum()).isEqualTo(Money.of(2300, Currency.PLN));
    }
    //endregion

    //region applyDiscountForCode method tests
    @Test
    void testingDiscount1() {
        // given
        Cart cart = new Cart(couponManager);
        couponManager.addDiscount(FLAT_10_PERCENT);
        cart.addItem(sampleProduct(2.30), 1000);

        // when
        cart.applyDiscountCode(FLAT_10_PERCENT.getCode());

        // then
        Assertions.assertThat(cart.overallSum())
                .isEqualByComparingTo(Money.of(BigDecimal.valueOf(2070.00), Currency.PLN));
    }

    private static Product sampleProduct(double v) {
        return Product.of("Butter", Money.of(BigDecimal.valueOf(v), Currency.PLN));
    }
    //endregion
    @Test
    void removingDiscountFromCart() {
        // given
        Cart cart = new Cart(couponManager);
        couponManager.addDiscount(FLAT_10_PERCENT);
        cart.addItem(sampleProduct(2.30), 1000);
        cart.applyDiscountCode(FLAT_10_PERCENT.getCode());

        // when
        cart.removeDiscountFromCart("code");

        // then
        Assertions.assertThat(cart.overallSum())
                .isEqualByComparingTo(Money.of(BigDecimal.valueOf(2300.00), Currency.PLN));
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
        public void removeDiscount(DiscountDefinition discountDefinition) {

        }
    }
    //endregion
}
