package org.example.store;

import lombok.RequiredArgsConstructor;
import org.example.product.ProductDefinition;
import org.example.product.ProductManager;

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

    // adding item to client's cart from repository if exists
    public void addItemToCart(String input) {
        customer.addToCart(input);
        out.println(input + " has been added to Your cart");
    }

    //removing item from client's cart if exist
    public void removeItemFromCart(String input) {
        customer.removeFromCart(input);
        out.println(input + " has been removed.");
    }

    //printing all products, with prices and sum of prices from client's shopping's
    public void printSummary() {
        for (Map.Entry<ProductDefinition, Integer> entry : customer.getCart().entrySet()) {
            out.println("Product: " +
                    entry.getKey().getProductName().getValue() +
                    ", quantity: " +
                    entry.getValue());
        }
        out.println(customer.overallSum());
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
