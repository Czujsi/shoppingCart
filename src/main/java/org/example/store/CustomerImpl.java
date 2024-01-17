package org.example.store;

import lombok.AllArgsConstructor;
import org.example.cart_components.Cart;
import org.example.coupons.DiscountDefinition;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.ProductManager;
import org.example.product.components.ProductId;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;

import static java.lang.System.out;

@AllArgsConstructor
public class CustomerImpl implements Customer {
    Cart cart;
    ProductManager productManager;
    Employee employee;

    @Override
    public void addToCart(String id) {
        if (!employee.checkIfExist(id)) {
            System.out.println("Sorry, we don't have that product.");
            return;
        }
        cart.addItem(productManager.getProductById(new ProductId(id)).orElseThrow(), 1);
        out.println(getAfterAddingInformation(id));
    }

    private String getAfterAddingInformation(String id) {
        ProductDefinition productDefinition = productManager.getProductById(new ProductId(id)).orElseThrow();
        return MessageFormat.format("{0}, has been added to cart with price: {1}", id, productDefinition.getPrice());
    }

    @Override
    public void removeFromCart(String input, int quantity) {
        if (!cart.has(input)) {
            System.out.println(MessageFormat.format("Sorry, You don''t have {0} in Your cart.", input));
            return;
        }
        cart.removeQuantity(input, quantity);
    }

    @Override
    public void removeAll(String input) {
        if (cart.has(input)) {
            cart.removeItem(input);
        }
    }

    @Override
    public void checkPrice() {

    }

    @Override
    public Map<ProductDefinition, Integer> getProducts() {
        return cart.getProducts();
    }

    @Override
    public Money overallSum() {
        return cart.overallSum();
    }

    @Override
    public void applyDiscount(String input) {
        cart.applyDiscountCode(input);
    }

    @Override
    public Set<DiscountDefinition> printDiscounts() {
        return cart.getDiscounts();
    }

    @Override
    public Cart getCart() {
        return cart;
    }
}
