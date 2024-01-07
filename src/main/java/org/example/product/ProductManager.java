package org.example.product;

import org.example.currency_exchange_money.Currency;

import java.math.BigDecimal;
import java.util.Collection;

public interface ProductManager {
    void addProduct(ProductDefinition productDefinition);

    void removeProduct(String name);

    void editProduct(String oldName, ProductDefinition productDefinition);

    boolean exist(String name);

    ProductDefinition getProductForName(String name);

    Collection<ProductDefinition> getAllProducts();

    BigDecimal getProductPrice(String input);

    Currency getProductCurrency(String input);
}
