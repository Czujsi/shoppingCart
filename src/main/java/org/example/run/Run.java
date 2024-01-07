package org.example.run;

import org.example.cart_components.Cart;
import org.example.cart_components.UserId;
import org.example.coupons.CouponManager;
import org.example.coupons.CouponManagerImpl;
import org.example.coupons.DiscountRepository;
import org.example.product.ProductManager;
import org.example.product.ProductManagerImpl;
import org.example.product.ProductRepositoryImpl;
import org.example.store.*;
import org.example.text_interface.TextInterface;
import org.example.user_interface.UserInterface;

public class Run {
    ProductRepositoryImpl productRepositoryImpl = new ProductRepositoryImpl();
    DiscountRepository discountRepository = new DiscountRepository();
    ProductManager productManager = new ProductManagerImpl(productRepositoryImpl);
    CouponManager couponManager = new CouponManagerImpl(discountRepository);
    UserId userId = new UserId(1L);
    Employee employee = new EmployeeImpl(productManager, couponManager);
    Customer customer = new CustomerImpl(new Cart(couponManager, userId), productManager, employee);
    Store store = new Store(productManager, customer, employee, couponManager);
    UserInterface textInterface = new TextInterface(store, employee, customer);

    public void runTextInterface() {
        textInterface.run();
    }
}
