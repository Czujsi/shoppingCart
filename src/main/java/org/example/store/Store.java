package org.example.store;

import lombok.RequiredArgsConstructor;
import org.example.cart_components.Cart;
import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.manager.CouponManager;
import org.example.user_interfaces.customer.Customer;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;
import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
public class Store {
    private final Customer customer;
    private final CouponManager couponManager;

    public void applyDiscountForCart(String input) {
        if (!couponManager.checkDiscountCode(input)) {
            out.println("Sorry, this discount code isn't available");
        }
        Cart cart = customer.getCart();
        cart.applyDiscountCode(input);

    }

    public void printCustomerDiscounts() {
        Cart cart = customer.getCart();
        out.println(cart.getDiscounts().stream()
                .map(Store::printDiscounts)
                .collect(joining(lineSeparator())));
    }

    private static String printDiscounts(DiscountDefinition discountDefinition) {
        return format("Discount code: {0}, discount value: {1}"
                , discountDefinition.getCode()
                , discountDefinition.getDiscountValue(discountDefinition.getCode()));
    }
}
