package org.example.store;

import org.assertj.core.api.Assertions;
import org.example.cart_components.Cart;
import org.example.cart_components.UserId;
import org.example.coupons.CouponManager;
import org.example.coupons.CouponManagerImpl;
import org.example.coupons.DiscountRepository;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.ProductManager;
import org.example.product.ProductManagerImpl;
import org.example.product.ProductRepositoryImpl;
import org.example.product.components.DateForProduct;
import org.example.product.components.ProductId;
import org.example.product.components.Price;
import org.example.product.components.ProductName;
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
    DateForProduct dateForProduct;

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
        dateForProduct = new DateForProduct(LocalDate.now());
    }

    @Test
    void writingTest() {
        Store store = new Store(productManager, customer, employee, couponManager);

        productManager.addProduct(new ProductDefinition(new ProductName("milk"), new Price(Money.of(2.54, Currency.PLN)), dateForProduct));
        store.printAllItemsFromStock();

        Assertions.assertThat("Product: milk, price: 2,54, date: 2024-01-17").isEqualTo(outputStreamCaptor.toString().trim());
    }
}