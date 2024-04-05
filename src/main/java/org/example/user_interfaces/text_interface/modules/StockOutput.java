package org.example.user_interfaces.text_interface.modules;

import lombok.RequiredArgsConstructor;
import org.example.product.ProductDefinition;
import org.example.product.manager.ProductManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static java.text.MessageFormat.format;

@Component
@RequiredArgsConstructor
public class StockOutput {
    private final ProductManager productManager;

    public void printStock() {
        System.out.println("Here are items that You can buy in our shop: ");
        List<ProductDefinition> allProducts = getAllProducts();
        for (int i = 0; i < allProducts.size(); i++) {
            System.out.println(getString(i, allProducts.get(i)));
        }
    }

    private List<ProductDefinition> getAllProducts() {
        return productManager.getAllProducts().stream().toList();
    }

    private String getString(int i, ProductDefinition productDefinition) {
        return format("{0} -> Product: {1}, price {2}", i, productDefinition.getName().getValue(), productDefinition.getPrice());
    }

    private int getNumber(String userInput) {
        return Integer.parseInt(userInput);
    }

    public String getIdByInput(String userInput) {
        UUID id = getAllProducts().get(getNumber(userInput)).getProductId().getValue();
        return id.toString();
    }
}
