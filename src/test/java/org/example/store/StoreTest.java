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
import org.example.product.ProductRepository;
import org.example.product.components.Price;
import org.example.product.components.ProductName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class StoreTest {
    ProductRepository productRepository;
    DiscountRepository discountRepository;
    ProductManager productManager;
    CouponManager couponManager;
    UserId userId = new UserId(1L);
    Employee employee;
    Customer customer;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();
        discountRepository = new DiscountRepository();
        productManager = new ProductManagerImpl(productRepository);
        couponManager = new CouponManagerImpl(discountRepository);
        employee = new EmployeeImpl(productManager);
        customer = new CustomerImpl(new Cart(couponManager, userId), productManager, employee);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void writingTest() {
        Store store = new Store(productManager, customer, employee);

        productManager.addProduct(new ProductDefinition(new ProductName("milk"), new Price(Money.of(2.54, Currency.PLN))));
        store.printAllItemsFromStock();

        Assertions.assertThat("Product: milk, price: 2,54").isEqualTo(outputStreamCaptor.toString().trim());
    }
}