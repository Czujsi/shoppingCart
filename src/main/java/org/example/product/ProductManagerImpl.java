package org.example.product;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;

@AllArgsConstructor
public class ProductManagerImpl implements ProductManager {

    private final ProductRepository<String, ProductDefinition> productRepository;

    @Override
    public void addProduct(ProductDefinition productDefinition) {
        productRepository.save(productDefinition);
    }

    @Override
    public void removeProduct(String name) {
        if (!productRepository.exists(name.toLowerCase())) {
            throw new IllegalArgumentException("You cannot remove product that does not exist");
        }
        productRepository.delete(name.toLowerCase());
    }

    @Override
    public void editProduct(String productName, ProductDefinition productDefinition) {
        productRepository.update(productName.toLowerCase(), productDefinition);
    }

    @Override
    public boolean exist(String name) {
        return productRepository.exists(name);
    }

    @Override
    public ProductDefinition getProductForName(String name) {
        return productRepository.get(name);
    }

    @Override
    public Collection<ProductDefinition> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public BigDecimal getProductPrice(String input) {
        return productRepository.get(input).getPrice().getAmount();
    }
}
