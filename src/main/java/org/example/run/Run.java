package org.example.run;

import org.example.cart_components.Cart;
import org.example.cart_components.UserId;
import org.example.coupons.CouponManager;
import org.example.coupons.CouponManagerImpl;
import org.example.coupons.DiscountRepository;
import org.example.product.ProductManager;
import org.example.product.ProductManagerImpl;
import org.example.product.ProductRepository;
import org.example.store.*;
import org.example.text_interface.TextInterface;
import org.example.user.UserManager;
import org.example.user_interface.UserInterface;

public class Run {
    ProductRepository productRepository = new ProductRepository();
    DiscountRepository discountRepository = new DiscountRepository();
    ProductManager productManager = new ProductManagerImpl(productRepository);
    CouponManager couponManager = new CouponManagerImpl(discountRepository);
    UserManager userManager = new UserManager();
    UserId userId = new UserId(1L);
    Cart cart = new Cart(couponManager, userId);
    Employee employee = new EmployeeImpl(productManager);
    Customer customer = new CustomerImpl(new Cart(couponManager, userId), productManager, employee);
    Store store = new Store(productManager, couponManager, userManager, cart, customer, employee);
    UserInterface textInterface = new TextInterface(store);
    TextInterface textInterface1 = new TextInterface(store);

    public void runTextInterface() {
        textInterface1.run();
    }
}
