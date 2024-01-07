package org.example.product;

import org.example.cart_components.Cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface ProductManager {
    void addProduct(ProductDefinition productDefinition);

    void removeProduct(String name);

    void editProduct(String oldName, ProductDefinition productDefinition);

    boolean exist(String name);

    ProductDefinition getProductForName(String name);

    Collection<ProductDefinition> getAllProducts();
}
