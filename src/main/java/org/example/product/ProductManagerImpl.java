package org.example.product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductManagerImpl implements ProductManager {
    private final ProductRepositoryInterface<String, ProductDefinition> productRepositoryInterface;

    @Override
    public void addProduct(ProductDefinition productDefinition) {
        productRepositoryInterface.save(productDefinition);
    }

    @Override
    public void removeProduct(String name) {
        if(!productRepositoryInterface.exists(name.toLowerCase())){
            throw new IllegalArgumentException("You cannot remove product that does not exist");
        }
        productRepositoryInterface.delete(name.toLowerCase());
    }

    @Override
    public void editProduct(String productName, ProductDefinition productDefinition) {
        productRepositoryInterface.update(productName.toLowerCase(), productDefinition);
    }

    @Override
    public boolean exist(String name) {
        return productRepositoryInterface.exists(name);
    }

    @Override
    public ProductDefinition getProductForName(String name) {
        return productRepositoryInterface.get(name);
    }

    @Override
    public void printAllProductsFromRepository() {
        productRepositoryInterface.getAll();
    }
}
