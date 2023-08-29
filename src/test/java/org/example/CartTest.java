package org.example;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;


class CartTest {

    //region addItem method test
    @Test
    void givenAnEmptyCart_whenAddedOneItem_thenCheckingIfDoesNotThrowAnyException() {
        //given
        Cart cart = new Cart();

        //when then
        Assertions.assertThatCode(() -> cart.addItem("Butter", 1)).doesNotThrowAnyException();
    }

    @Test
    void givenAnEmptyCart_whenAddedOneItem_thenCheckingIfIsItTrueThatItemWasAdded() {
        //given
        Cart cart = new Cart();

        //when
        cart.addItem("Butter", 1);

        //then
        Assertions.assertThat(cart.has("Butter")).isTrue();

    }

    @Test
    void givenAnEmptyCart_whenNotModifyingContent_thenCheckingIfItIsFalseThatItemDoesNotExistInContent() {
        //given
        Cart cart = new Cart();

        //when-then
        Assertions.assertThat(cart.has("Butter")).isFalse();

    }

    @Test
    void givenAnEmptyCart_whenAddingOneItemWith99Quantity_thenCheckingIfTrueThatCartHasAddedItem() {
        //given
        Cart cart = new Cart();

        //when
        cart.addItem("Butter", 99);

        //then
        Assertions.assertThat(cart.has("Butter")).isTrue();

    }

    @Test
    void givenAnEmptyCart_whenAddingItemWhit0Quantity_thenCheckingIfItIsFalseThatItemIsInCart() {
        //given
        Cart cart = new Cart();

        //when
        cart.addItem("Butter", 0);

        //then
        Assertions.assertThat(cart.has("Butter")).isFalse();

    }

    @Test
    void givenAnEmptyCart_thenAddingItemWithNullProductNameAndCheckingIfExceptionIsThrown() {
        //given
        Cart cart = new Cart();

        //when-then
        Assertions.assertThatThrownBy(() -> cart.addItem(null, 1)).hasMessage("You cannot add null product name");

    }

    @Test
    void givenAnEmptyCart_whenAddingItemWithNegativeQuantity_thenCheckingIfExceptionIsThrown() {
        //given
        Cart cart = new Cart();

        //when
        ThrowableAssert.ThrowingCallable addItem = () -> cart.addItem("Butter", -2);

        //then
        Assertions.assertThatThrownBy(addItem).hasMessage("You cannot add negative value of product");

    }

    @Test
    void givenACartWithOneItem_whenAddedTheSameItem_thenQuantityIsASumOfItems() {
        //given
        Cart cart = new Cart();
        cart.addItem("Butter", 1);

        //when
        cart.addItem("Butter", 1);

        //then
        Assertions.assertThat(cart.has("Butter")).isTrue();
        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(2);
    }

    @Test
    void givenAnEmptyCart_whenAddingItemWithOneQuantity_thenAddingItemWith2QuantityAndCheckingIfItemIsInContentAndQuantityIsSummed() {
        //given
        Cart cart = new Cart();

        //when
        cart.addItem("Butter", 1);

        //then
        cart.addItem("Butter", 2);
        Assertions.assertThat(cart.has("Butter")).isTrue();
        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(3);
    }

    @Test
    void givenAnEmptyCart_whenAddingTwoSameItemsTwoTimesWithDifferentQuantity_ThenCheckingIfContentHasItemAndIfQuantityIsSummedUp() {
        //given
        Cart cart = new Cart();

        //when
        cart.addItem("Butter", 2);
        cart.addItem("Butter", 1);

        //then
        Assertions.assertThat(cart.has("Butter")).isTrue();
        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(3);
    }

    @Test
    void givenAnEmptyCart_thenCheckingCartContentIfNotAddedItemQuantityIsZero() {
        Cart cart = new Cart();

        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(0);
    }

    @Test
    void giveACartWithOneItem_whenRemovingItem_checkingIfItemWasRemovedWithQuantity() {
        //given
        Cart cart = new Cart();
        cart.addItem("Butter", 1);

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
        Cart cart = new Cart();

        cart.addItem("Butter", 1);

        //when
        cart.removeItem("Butter");

        //then
        Assertions.assertThat(cart.has("Butter")).isFalse();
        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(0);
    }

    @Test
    void givenCartWithTwoItems_whenRemovingOneItem_thenCartHasItemIsTrue() {
        //given
        Cart cart = new Cart();

        cart.addItem("Butter", 3);

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
        Cart cart = new Cart();
        cart.addItem("Butter", 2);

        //when
        cart.removeQuantity("Butter", 1);

        Assertions.assertThat(cart.quantityOf("Butter")).isEqualTo(1);

    }

    @Test
    void givenCartWithTwoItem_whenRemovingQuantityOfItemThatIsBiggerThatActualQuantity_thenThrowException() {
        //given
        Cart cart = new Cart();
        cart.addItem("Butter", 2);

        //when
        ThrowableAssert.ThrowingCallable removeQuantity = () -> cart.removeQuantity("Butter", 3);

        Assertions.assertThatThrownBy(removeQuantity).hasMessage("You cannot remove more product than you have in cart");

    }

    @Test
    void givenCartWithTwoItem_whenRemovingNegativeValueOfQuantity_thenThrowException() {
        //given
        Cart cart = new Cart();
        cart.addItem("Butter", 2);

        //when
        ThrowableAssert.ThrowingCallable removeQuantity = () -> cart.removeQuantity("Butter", -3);

        Assertions.assertThatThrownBy(removeQuantity).hasMessage("You cannot remove negative value of products");

    }


    //endregion - -


}