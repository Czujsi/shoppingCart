package org.example.run;

import org.example.cart_components.Cart;
import org.example.cart_components.UserId;
import org.example.coupons.discount.repository.DiscountRepository;
import org.example.coupons.manager.CouponManager;
import org.example.coupons.manager.CouponManagerImpl;
import org.example.product.manager.ProductManager;
import org.example.product.manager.ProductManagerImpl;
import org.example.product.repository.ProductRepositoryFileImpl;
import org.example.store.Store;
import org.example.store.customer.Customer;
import org.example.store.customer.CustomerImpl;
import org.example.user_interfaces.modules.*;
import org.example.user_interfaces.modules.employee.Employee;
import org.example.user_interfaces.modules.employee.EmployeeImpl;
import org.example.user_interfaces.text_interface.TextInterface;
import org.example.user_interfaces.user.UserInterface;

public class Run {
    ProductRepositoryFileImpl productRepositoryFile = new ProductRepositoryFileImpl();
    DiscountRepository discountRepository = new DiscountRepository();
    ProductManager productManager = new ProductManagerImpl(productRepositoryFile);
    StockOutput stockOutput = new StockOutput(productManager);
    CouponManager couponManager = new CouponManagerImpl(discountRepository);
    UserId userId = new UserId(1L);
    Employee employee = new EmployeeImpl(productManager, couponManager);
    Customer customer = new CustomerImpl(new Cart(couponManager, userId), productManager, employee);
    Store store = new Store(customer, couponManager);
    Summary summary = new Summary(store, customer, employee);
    StockOperations stockOperations = new StockOperations(employee, stockOutput);
    DiscountOperations discountOperations = new DiscountOperations(couponManager);
    ChoosingItems choosingItems = new ChoosingItems(customer, employee, productManager, summary, stockOutput);
    UserInterface textInterface = new TextInterface(employee, choosingItems, stockOperations, productManager, discountOperations);

    public void runTextInterface() {
        textInterface.run();
    }
}
