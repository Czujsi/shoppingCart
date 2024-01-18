package org.example.store;

import org.assertj.core.api.Assertions;
import org.example.cart_components.Cart;
import org.example.cart_components.UserId;
import org.example.coupons.manager.CouponManager;
import org.example.coupons.manager.CouponManagerImpl;
import org.example.coupons.discount.repository.DiscountRepository;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.manager.ProductManager;
import org.example.product.manager.ProductManagerImpl;
import org.example.product.repository.ProductRepositoryImpl;
import org.example.product.components.CreationDate;
import org.example.product.components.Price;
import org.example.product.components.Name;
import org.example.store.customer.Customer;
import org.example.store.customer.CustomerImpl;
import org.example.store.employee.Employee;
import org.example.store.employee.EmployeeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

class StoreTest {
    ProductRepositoryImpl productRepositoryImpl;
    DiscountRepository discountRepository;
    ProductManager productManager;
    CouponManager couponManager;
    UserId userId = new UserId(1L);
    Employee employee;
    Customer customer;
    CreationDate creationDate;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        productRepositoryImpl = new ProductRepositoryImpl();
        discountRepository = new DiscountRepository();
        productManager = new ProductManagerImpl(productRepositoryImpl);
        couponManager = new CouponManagerImpl(discountRepository);
        employee = new EmployeeImpl(productManager, couponManager);
        customer = new CustomerImpl(new Cart(couponManager, userId), productManager, employee);
        System.setOut(new PrintStream(outputStreamCaptor));
        creationDate = new CreationDate(LocalDate.now());
    }

    @Test
    void writingTest() {
        Store store = new Store(productManager, customer, employee, couponManager);

        productManager.addProduct(new ProductDefinition(new Name("milk"), new Price(Money.of(2.54, Currency.PLN)), creationDate));
        store.printAllItemsFromStock();

        Assertions.assertThat("Product: milk, price: 2,54, date: " + creationDate).isEqualTo(outputStreamCaptor.toString().trim());
    }
}