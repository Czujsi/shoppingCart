package org.example.run;

import org.example.account.UserId;
import org.example.cart_components.Cart;
import org.example.cart_components.manager.CartManager;
import org.example.cart_components.manager.CartManagerImpl;
import org.example.cart_components.repository.CartRepository;
import org.example.cart_components.repository.CartRepositoryImpl;
import org.example.coupons.discount.repository.DiscountRepository;
import org.example.coupons.manager.CouponManager;
import org.example.coupons.manager.CouponManagerImpl;
import org.example.product.converters.CsvConverter;
import org.example.product.manager.ProductManager;
import org.example.product.manager.ProductManagerImpl;
import org.example.product.repository.ProductRepositoryFileImpl;
import org.example.store.Store;
import org.example.user_interfaces.customer.Customer;
import org.example.user_interfaces.customer.CustomerImpl;
import org.example.user_interfaces.modules.*;
import org.example.user_interfaces.modules.employee.Employee;
import org.example.user_interfaces.modules.employee.EmployeeImpl;
import org.example.user_interfaces.text_interface.TextInterface;
import org.example.user_interfaces.user.UserInterface;


public class Run {
//    CsvConverter csvConverter = new CsvConverter();
//    ProductRepositoryFileImpl productRepositoryFile = new ProductRepositoryFileImpl(csvConverter);
//    CartRepository<UserId, Cart> cartRepository = new CartRepositoryImpl();
//    DiscountRepository discountRepository = new DiscountRepository();
//    ProductManager productManager = new ProductManagerImpl(productRepositoryFile);
//    StockOutput stockOutput = new StockOutput(productManager);
//    CouponManager couponManager = new CouponManagerImpl(discountRepository);
//    UserId userId = new UserId();
//    Employee employee = new EmployeeImpl(productManager, couponManager);
//    CartManager cartManager = new CartManagerImpl(cartRepository);
//    Customer customer = new CustomerImpl(new Cart(couponManager, userId), productManager, employee, cartManager, couponManager);
//    Store store = new Store(customer, couponManager);
//    Summary summary = new Summary(store, customer, employee);
//    StockOperations stockOperations = new StockOperations(employee, stockOutput);
//    DiscountOperations discountOperations = new DiscountOperations(couponManager);
//    ChoosingItems choosingItems = new ChoosingItems(customer, employee, productManager, summary, stockOutput);
//    UserInterface textInterface = new TextInterface(choosingItems, stockOperations, productManager, discountOperations);
//
//    public void runTextInterface() {
//        textInterface.run();
//    }
}
