package org.example.product.repository;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.fileUtilities.FileUtil;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;
import org.example.product.csv_operations.mappers.Mapper;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class InFileRepository implements ProductRepository<ProductId, ProductDefinition> {

    private final File csvFile;
    private final FileUtil fileUtil;
    private final Mapper<ProductDefinition, String> mapper;
    private final Collection<ProductDefinition> productDefinitions = new HashSet<>();


    @Override
    public void save(ProductDefinition productDefinition) {
        String serialize = mapper.deserialize(productDefinition);
        fileUtil.writeToFile(csvFile, serialize);
    }

    @Override
    public void delete(ProductId productId) {
        fileUtil.deleteFromFile(csvFile, productId);
    }

    @Override
    public boolean exists(ProductId productId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ProductDefinition> findById(ProductId productId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<ProductDefinition> getAll() {
        productDefinitions.clear();
        List<String> productsAsText = fileUtil.readFile(csvFile);
        for (String product : productsAsText) {
            ProductDefinition serializedProducts = mapper.serialize(product);
            productDefinitions.add(serializedProducts);
        }
        return productDefinitions;
    }

    @Override
    public List<String> getIdForName(String name) {
        return getAll().stream()
                .filter(p -> p
                        .getName()
                        .getValue().equals(name))
                .map(p -> p
                        .getProductId()
                        .getValue()
                        .toString())
                .toList();
    }

    @Override
    public Collection<ProductDefinition> refreshStock() {
        return getAll();
    }
}


