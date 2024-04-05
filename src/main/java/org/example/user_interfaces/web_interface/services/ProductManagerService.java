package org.example.user_interfaces.web_interface.services;

import lombok.RequiredArgsConstructor;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;
import org.example.product.manager.ProductManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManagerService {
    private final ProductManager productManager;

    public List<ProductDefinition> getAllProducts() {
        return productManager.getAllProducts().stream().toList();
    }

    public Optional<ProductDefinition> getProductById(String productId) {
        return productManager.getProductById(new ProductId(productId));
    }
}
