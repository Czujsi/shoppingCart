package org.example.product.repository;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ToString
@EqualsAndHashCode
public class ProductRepositoryImpl implements ProductRepository<ProductId, ProductDefinition> {
    private final Set<ProductDefinition> productRepository = new HashSet<>();

    @Override
    public void save(ProductDefinition object) {
        productRepository.add(object);
    }

    @Override
    public void delete(ProductId productId) {
        productRepository.removeIf(p -> p.getProductId().equals(productId));
    }

    public Collection<ProductDefinition> getIdForName(String name) {
        return productRepository.stream()
                .filter(v -> v.getName().getValue().equalsIgnoreCase(name))
                .toList();
    }

    @Override
    public boolean exists(ProductId productId) {
        return productRepository.stream()
                .anyMatch(v -> v.getProductId().equals(productId));
    }

    public Optional<ProductDefinition> findById(ProductId productId) {
        return productRepository.stream()
                .filter(v -> v.getProductId().equals(productId))
                .findFirst();
    }

    @Override
    public Collection<ProductDefinition> getAll() {
        return productRepository;
    }
}
