package org.example.text_interface;

import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.ProductInMemoryRepository;
import org.example.product.ProductManager;
import org.example.product.ProductManagerImpl;
import org.example.product.components.Price;
import org.example.product.components.ProductName;

import java.math.BigDecimal;
import java.util.Scanner;

public class EmployeeTextInterface {
    private final ProductManager productManager;
    private final Scanner scanner = new Scanner(System.in);

    public EmployeeTextInterface(ProductManager productManager) {
        this.productManager = productManager;
    }

    public void welcomeEmployee() {
        System.out.println("Welcome employee you are in full shop aces mode");
        System.out.println("For adding new products to shop type: add");
        String option = scanner.nextLine().toLowerCase();
        if (option.equals("add")) {
            System.out.println("Type product name");
            System.out.println("Type product price");
            System.out.println("Type product price currency");

            String productName = scanner.nextLine().toLowerCase();
            String productPriceOption = scanner.nextLine().toLowerCase();
            BigDecimal productPrice = BigDecimal.valueOf(Double.parseDouble(productPriceOption));

            addProduct(productName, productPrice);

            Shop shop = new Shop();
            shop.run();
        }
    }

    void addProduct(String productName, BigDecimal productPrice) {
        productManager.addProduct(new ProductDefinition(new ProductName(productName), new Price(Money.of(productPrice, Currency.PLN))));
        System.out.println("Product added with name: " + productName + " ,at price: " + productPrice + " and currency: " + Currency.PLN);
    }
}
