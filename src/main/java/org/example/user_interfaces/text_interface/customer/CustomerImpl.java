package org.example.user_interfaces.text_interface.customer;

import lombok.AllArgsConstructor;
import org.example.account.UserId;
import org.example.cart_components.Cart;
import org.example.cart_components.CartId;
import org.example.cart_components.manager.CartManager;
import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.manager.CouponManager;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;
import org.example.product.manager.ProductManager;
import org.example.user_interfaces.text_interface.modules.employee.Employee;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

import static java.lang.System.out;
import static java.text.MessageFormat.format;

@Component
@AllArgsConstructor
public class CustomerImpl implements Customer {
    private Cart cart;
    private final ProductManager productManager;
    private final Employee employee;
    private final CartManager cartManager;
    private final CouponManager couponManager;
    private final UserId userId = UserId.createId();
    private final CartId cartId = CartId.createId();

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
        return MessageFormat.format("Product: {0}, has been added to cart with price: {1}",
                productDefinition.getName().getValue(),
                productDefinition.getPrice());
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
    public List<ProductDefinition> getProducts() {
        List<ProductDefinition> productDefinitions = new ArrayList<>();
        for (Map.Entry<ProductDefinition, Integer> entry : cart.getProducts().entrySet()) {
            productDefinitions.add(entry.getKey());
        }
        return productDefinitions;
    }

    public Map<ProductDefinition, Integer> getProductsMap() {
        return cart.getProducts();
    }

    @Override
    public Money overallSum() {
        return cart.overallSum();
    }

    @Override
    public void applyDiscount(String code) {
        cart.applyDiscountCode(code);
    }

    @Override
    public Set<DiscountDefinition> printDiscounts() {
        return cart.getDiscounts();
    }

    @Override
    public Cart getCart() {
        return cart;
    }


    @Override
    public String getId() {
        return userId.getValue().toString();
    }

    @Override
    public void saveCart() {
        cartManager.addCart(userId, cart);
    }

    @Override
    public void createCart() {
        this.cart = cartManager.addCart(userId, new Cart(cartId, couponManager));
    }

    @Override
    public void deleteCart() {
        cartManager.deleteCart(userId);
    }

    @Override
    public Collection<Cart> getCarts() {
        return cartManager.getCartByUserId(userId).stream().toList();
    }

    @Override
    public Cart chooseCart(String input) {
        if (getNumber(input) > getCarts().size()) {
            out.println("Wrong command, try again");
        }
        return cart = getCarts().stream().toList().get(getNumber(input));
    }

    public void printCarts() {
        System.out.println("Here are your carts: ");
        Collection<Cart> allCarts = getCarts();
        for (int i = 0; i < allCarts.size(); i++) {
            chooseCart(String.valueOf(i));
            System.out.println(getString(i, allCarts.stream().toList().get(i)));
        }
    }

    private String getString(int i, Cart cart) {
        return format("{0} -> Cart:\nProducts: {1}, \nsum: {2}", i, getProducts().toString(), cart.overallSum());
    }

    private int getNumber(String userInput) {
        return Integer.parseInt(userInput);
    }
}
