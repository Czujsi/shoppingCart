package org.example.product.manager;

import lombok.RequiredArgsConstructor;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;
import org.example.product.repository.ProductRepository;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductManagerImpl implements ProductManager {
    private final ProductRepository<ProductId, ProductDefinition> productRepository;

    @Override
    public void addProduct(ProductDefinition productDefinition) {
        productRepository.save(productDefinition);
    }

    @Override
    public void removeProduct(ProductId mappedId) {
        if (!productRepository.exists(mappedId)) {
            throw new IllegalArgumentException("You cannot remove product that does not exist");
        }
        productRepository.delete(mappedId);
    }

    @Override
    public void updateProductName(ProductId id, String newName) {
        getProductById(id).ifPresent(p -> p.updateName(newName));
    }

    @Override
    public void updateProductPrice(ProductId id, Money newPrice) {
        getProductById(id).ifPresent(p -> p.updatePrice(newPrice));
    }

    @Override
    public boolean exist(ProductId productId) {
        return productRepository.exists(productId);
    }

    @Override
    public Optional<ProductDefinition> getProductById(ProductId id) {
        return productRepository.findById(id);
    }

    @Override
    public Collection<ProductDefinition> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public Collection<ProductDefinition> refreshStock() {
        return productRepository.refreshSock();
    }
}
