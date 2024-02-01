package org.example.run;

import org.example.cart_components.Cart;
import org.example.cart_components.UserId;
import org.example.coupons.manager.CouponManager;
import org.example.coupons.manager.CouponManagerImpl;
import org.example.coupons.discount.repository.DiscountRepository;
import org.example.product.manager.ProductManager;
import org.example.product.manager.ProductManagerImpl;
import org.example.product.repository.ProductRepositoryImpl;
import org.example.store.*;
import org.example.store.customer.Customer;
import org.example.store.customer.CustomerImpl;
import org.example.store.employee.Employee;
import org.example.store.employee.EmployeeImpl;
import org.example.user_interfaces.modules.ChoosingItems;
import org.example.user_interfaces.modules.StockOperations;
import org.example.user_interfaces.modules.Summary;
import org.example.user_interfaces.text_interface.TextInterface;
import org.example.user_interface.UserInterface;

public class Run {
    ProductRepositoryImpl productRepositoryImpl = new ProductRepositoryImpl();
    DiscountRepository discountRepository = new DiscountRepository();
    ProductManager productManager = new ProductManagerImpl(productRepositoryImpl);
    CouponManager couponManager = new CouponManagerImpl(discountRepository);
    UserId userId = new UserId(1L);
    Employee employee = new EmployeeImpl(productManager, couponManager);
    Customer customer = new CustomerImpl(new Cart(couponManager, userId), productManager, employee);
    Store store = new Store(customer, couponManager);
    Summary summary = new Summary(store, customer, employee);
    StockOperations stockOperations = new StockOperations(employee);
    ChoosingItems choosingItems = new ChoosingItems(customer, employee, productManager, summary);
    UserInterface textInterface = new TextInterface(employee, choosingItems, stockOperations);

    public void runTextInterface() {
        textInterface.run();
    }
}
