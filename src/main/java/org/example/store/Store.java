package org.example.store;

import lombok.RequiredArgsConstructor;
import org.example.cart_components.Cart;
import org.example.coupons.manager.CouponManager;
import org.example.coupons.discount.DiscountDefinition;
import org.example.product.ProductDefinition;
import org.example.product.manager.ProductManager;
import org.example.store.customer.Customer;
import org.example.store.employee.Employee;

import java.util.Map;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;
import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
public class Store {
    private final ProductManager productManager;
    private final Customer customer;
    private final Employee employee;
    private final CouponManager couponManager;

    // adding item to client's cart from repository if exists
    public void addItemToCart(String input) {
        customer.addToCart(input);
        out.println(input + " has been added to Your cart");
    }

    //removing item from client's cart if exist
    public void removeItemFromCart(String input, int quantity) {
        customer.removeFromCart(input, quantity);
    }

    //printing all products, with prices and sum of prices from client's shopping's
    public void printSummary() {
        for (Map.Entry<ProductDefinition, Integer> entry : customer.getProducts().entrySet()) {
            out.println("Product: " +
                    entry.getKey().getName().getValue() +
                    ", quantity: " +
                    entry.getValue());
        }
        out.println(customer.overallSum());
    }

    //check if item exists
    public boolean checkIfExists(String input) {
        return employee.checkIfExist(input);
    }

    //adding item to store(productRepository), method for employee
    public void addItemToStock() {
        employee.addToStock();
    }

    //updating item on stock if exists, method for employee
    private void updateItemOnStock() {
        employee.updateOnStock();
    }

    //removing item from stock if exists, method for employee
    public void removeItemFromStock(String input) {
        employee.removeFromStock(input);
        customer.removeAll(input);
        out.println("Sorry, " + input + " is no longer available in our store");
    }

    //printing all items from stock, method from employee and client
    public void printAllItemsFromStock() {
        out.println((productManager.getAllProducts().stream()
                .map(Store::getString)
                .collect(joining(lineSeparator()))));
    }
    private static String getString(ProductDefinition pd) {

        return format("Product: {0}, price: {1}, date: {2}", pd.getName().getValue(), pd.getPrice().getAmount(), pd.getCreationDate());
    }
    public void applyDiscountForCart(String input){
        if (!couponManager.checkDiscountCode(input)){
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
        return format("Discount code: {0}, discount value: {1}", discountDefinition.getCode(),discountDefinition.getDiscountValue(discountDefinition.getCode()));
    }
}
