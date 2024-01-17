package org.example.product;

import org.example.currency_exchange_money.Money;
import org.example.product.components.ProductId;

import java.util.Collection;
import java.util.Optional;

public interface ProductManager {
    void addProduct(ProductDefinition productDefinition);

    void removeProduct(ProductId mappedId1);

    void updateProductName(ProductId id, String newName);

    void updateProductPrice(ProductId id, Money money);

    boolean exist(ProductId productId);

    Optional<ProductDefinition> getProductById(ProductId productId);

    Collection<ProductDefinition> getAllProducts();
}
