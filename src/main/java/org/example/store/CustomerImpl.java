package org.example.store;

import lombok.AllArgsConstructor;
import org.example.cart_components.Cart;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.ProductManager;

import java.util.Map;

@AllArgsConstructor
public class CustomerImpl implements Customer {
    Cart cart;
    ProductManager productManager;
    Employee employee;

    @Override
    public void addToCart(String input) {
        if (!employee.checkIfExist(input)) {
            System.out.println("Sorry, we don't have that product.");
            return;
        }
        cart.addItem(productManager.getProductForName(input), 1);
    }

    @Override
    public void removeFromCart(String input) {
        if (!cart.has(input)) {
            System.out.println("Sorry, You don't have " + input + " in Your cart.");
            return;
        }
        cart.removeItem(input);
    }

    @Override
    public void checkPrice() {

    }

    @Override
    public Map<ProductDefinition, Integer> getCart() {
        return cart.getProducts();
    }

    @Override
    public Money overallSum() {
        return cart.overallSum();
    }
}
