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

public class Products {
    ProductInMemoryRepository productInMemoryRepository = new ProductInMemoryRepository();
    ProductManagerImpl productManager = new ProductManagerImpl(productInMemoryRepository);
    void addProduct(){

    }
    ProductDefinition milk = new ProductDefinition(new ProductName("Milk"), new Price(Money.of(BigDecimal.valueOf(2.50), Currency.PLN)));
    ProductDefinition butter = new ProductDefinition(new ProductName("Butter"), new Price(Money.of(BigDecimal.valueOf(4.50), Currency.PLN)));
    ProductDefinition apple = new ProductDefinition(new ProductName("Apple"), new Price(Money.of(BigDecimal.valueOf(0.50), Currency.PLN)));
}
