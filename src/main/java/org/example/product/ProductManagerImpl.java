package org.example.product;

import lombok.AllArgsConstructor;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.components.DateForProduct;
import org.example.product.history.ProductHistory;

import java.math.BigDecimal;
import java.util.Collection;

@AllArgsConstructor
public class ProductManagerImpl implements ProductManager {

    private final ProductRepository<String, ProductDefinition> productRepository;
    private final ProductHistory productHistory = new ProductHistory();

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
    public void updateProductName(String productName, String newName) {
            getProductForName(productName).updateName(newName);

            addProduct(getProductForName(productName));
            removeProduct(productName);
    }

    @Override
    public void updateProductPrice(String productName, Money newPrice) {
            getProductForName(productName).updatePrice(newPrice);

            addProduct(getProductForName(productName));
            removeProduct(productName);
    }

    @Override
    public boolean exist(String productName) {
        return productRepository.exists(productName);
    }

    @Override
    public ProductDefinition getProductForName(String productName) {
        return productRepository.get(productName);
    }

    @Override
    public Collection<ProductDefinition> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public BigDecimal getProductPrice(String input) {
        return productRepository.get(input).getPrice().getAmount();
    }

    @Override
    public Currency getProductCurrency(String input) {
        return productRepository.get(input).getPrice().getCurrency();
    }

    @Override
    public DateForProduct getDateForProduct(String productName) {
        return productRepository.get(productName).getCreationDate();
    }
}
