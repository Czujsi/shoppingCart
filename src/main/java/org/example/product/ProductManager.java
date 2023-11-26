package org.example.product;

public interface ProductManager {
    void addProduct(ProductDefinition productDefinition);
    void removeProduct(String name);

    void editProduct(String oldName, ProductDefinition productDefinition);
}
