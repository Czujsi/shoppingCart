package org.example.store;

import lombok.RequiredArgsConstructor;
import org.example.cart_components.Cart;
import org.example.coupons.CouponManager;
import org.example.product.ProductDefinition;
import org.example.product.ProductManager;
import org.example.user.UserManager;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.*;
import static java.text.MessageFormat.*;
import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class Store {
    private final ProductManager productManager;
    private final CouponManager couponManager;
    private final UserManager userManager;
    private final Cart cart;
    private final Customer customer;
    private final Employee employee;
    Scanner scanner = new Scanner(in);

    // adding item to client's cart from repository if exists
    public void addItemToCart(String input) {
        customer.addToCart(input);
        out.println(input + " has been added to Your cart");
    }

    //removing item from client's cart if exist
    public void removeItemFromCart(String input) {
        out.println(input + " has been removed.");
        customer.removeFromCart(input);
    }

    //printing all products, with prices and sum of prices from client's shopping's
    public void printSummary() {
        for (Map.Entry<ProductDefinition, Integer> entry : cart.getProducts().entrySet()) {
            out.println("Product: " +
                    entry.getKey().getProductName().getValue() +
                    ", quantity: " +
                    entry.getValue().toString());
        }
        out.println(cart.overallSum());
    }

    //check if item exists
    public boolean checkIfExists(String input) {
        return productManager.exist(input);
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
    private void removeItemFromStock() {
        employee.removeFromStock();
    }

    //searching and printing item properties from stock, method for employee
    public void searchForItem(String productName) {
        if (!productManager.exist(productName)) {
            out.println("Sorry, but we don't have that product on stock");
            return;
        }
        out.println(productManager.getProductForName(productName));
    }

    //printing all items from stock, method from employee and client
    public void printAllItemsFromStock() {
        out.println((productManager.getAllProducts().stream()
                .map(Store::getString)
                .collect(joining(lineSeparator()))));
    }

    private static String getString(ProductDefinition pd) {
        return format("Product: {0}, price: {1}", pd.getProductName().getValue(), pd.getPrice().getAmount());
    }

}
