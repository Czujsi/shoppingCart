package org.example.product.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;
import org.example.product.repository.ProductRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductManagerImpl implements ProductManager {
    private final ProductRepository<ProductId, ProductDefinition> productRepository;

    @Override
    public void createProduct(ProductDefinition productDefinition) {
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
        refreshStock();
        return productRepository.getAll();
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public Collection<ProductDefinition> refreshStock() {
        log.debug("refreshing stock");
        return productRepository.refreshStock();
    }
}
