package org.example.product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductManagerImpl implements ProductManager {
    private final ProductRepository<String, ProductDefinition> productRepository;

    @Override
    public void addProduct(ProductDefinition productDefinition) {
        productRepository.save(productDefinition);
    }

    @Override
    public void removeProduct(String name) {
        if(!productRepository.exists(name.toLowerCase())){
            throw new IllegalArgumentException("You cannot remove product that does not exist");
        }
        productRepository.delete(name.toLowerCase());
    }

    @Override
    public void editProduct(String productName, ProductDefinition productDefinition) {
        productRepository.update(productName.toLowerCase(), productDefinition);
    }
}
