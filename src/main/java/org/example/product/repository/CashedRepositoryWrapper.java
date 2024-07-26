package org.example.product.repository;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;

import java.util.*;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@Slf4j
public class CashedRepositoryWrapper<ID extends ProductId, TYPE extends ProductDefinition> implements ProductRepository<ID, TYPE> {
    private final ProductRepository<ID, TYPE> productRepository;
    private final Set<ProductDefinition> products = new HashSet<>();


    @Override
    public void save(TYPE object) {
        this.productRepository.save(object);
        products.add(object);

    }

    @Override
    public void delete(ID id) {
        this.productRepository.delete(id);
        ProductDefinition product = getProductDefinition(id);
        products.remove(product);
    }

    @Override
    public boolean exists(ID id) {
        ProductDefinition product = getProductDefinition(id);
        return products.contains(product);
    }

    @Override
    public Optional<ProductDefinition> findById(ProductId productId) {
        getAll();
        ProductDefinition product = getProductDefinition(productId);
        return Optional.of(product);
    }

    @Override
    public Collection<ProductDefinition> getAll() {
        Collection<ProductDefinition> allProducts = this.productRepository.getAll();
        products.addAll(allProducts);
        return products;
    }

    @Override
    public List<String> getIdForName(String name) {
        return findProductsIdWithSpecifiedName(name);
    }

    @Override
    public Collection<ProductDefinition> refreshStock() {
        Collection<ProductDefinition> allProducts = this.productRepository.getAll();
        products.addAll(allProducts);
        return products;
    }

    private List<String> findProductsIdWithSpecifiedName(String name) {
        return products.stream()
                .filter(productDefinition -> productDefinition
                        .getName()
                        .getValue()
                        .equals(name))
                .map(p -> p
                        .getProductId()
                        .getValue()
                        .toString())
                .toList();
    }

    private ProductDefinition getProductDefinition(ProductId productId) {
        return products
                .stream()
                .filter(
                        productDefinition -> productDefinition
                                .getProductId()
                                .getValue()
                                .toString()
                                .equals(
                                        productId.getValue()
                                                .toString()))
                .toList().getFirst();
    }
}
