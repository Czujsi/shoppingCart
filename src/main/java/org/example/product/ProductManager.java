package org.example.product;

import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.components.DateForProduct;

import java.math.BigDecimal;
import java.util.Collection;

public interface ProductManager {
    void addProduct(ProductDefinition productDefinition);

    void removeProduct(String name);

    void updateProductName(String oldName, String newName);

    void updateProductPrice(String name, Money money);

    boolean exist(String name);

    ProductDefinition getProductForName(String name);

    Collection<ProductDefinition> getAllProducts();

    BigDecimal getProductPrice(String input);

    Currency getProductCurrency(String input);
    DateForProduct getDateForProduct(String productName);
}
