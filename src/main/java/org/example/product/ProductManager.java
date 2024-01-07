package org.example.product;

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
}
