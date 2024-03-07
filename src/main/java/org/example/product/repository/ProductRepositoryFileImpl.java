package org.example.product.repository;

import lombok.RequiredArgsConstructor;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;
import org.example.product.converters.CsvConverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

@RequiredArgsConstructor
public class ProductRepositoryFileImpl implements ProductRepository<ProductId, ProductDefinition> {
    private final CsvConverter csvConverter;
    Set<ProductDefinition> products = new HashSet<>();
    File file = new File("src/main/resources/test.csv");

    @Override
    public void save(ProductDefinition productDefinition) {
        // sprawdzam czy w liście produktów którą pobrałem z pliku, ta definicja produktu już istnieje, porównując id produktu
        if (exists(productDefinition.getProductId())) {
            throw new IllegalArgumentException("Product already exists");
        }
        // następnie dodaję ten produkt do listy w pamięci, na której pracuję, dzięki temu nie muszę za każdym razem odczytywać pliku
        products.add(productDefinition);

        // tutaj najpierw konwertuję produkt do formatu csv za pomocą metody, a następnie zapisuję w pliku
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(convertToCSV(productDefinition));
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String convertToCSV(ProductDefinition productDefinition) {
        return String.format("%s;%s;%s;%s",
                productDefinition.getName().getValue(),
                productDefinition.getPrice().getAmount().toString(),
                productDefinition.getCreationDate(),
                productDefinition.getProductId().getValue());
    }

    @Override
    public void delete(ProductId productId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean exists(ProductId productId) {
        return products.stream().anyMatch(p -> p.getProductId().equals(productId));
    }

    @Override
    public Optional<ProductDefinition> findById(ProductId productId) {
        return products.stream()
                .filter(v -> v.getProductId().equals(productId))
                .findFirst();
    }

    @Override
    public Collection<ProductDefinition> getAll() {
        return products;
    }

    public Collection<ProductDefinition> refreshStock() {
        CsvFileReader();
        return products;
    }

    private void CsvFileReader() {
        List<ProductDefinition> productDefinitions = csvConverter.convertFromFile(file.getPath());
        products.addAll(productDefinitions);
    }

    @Override
    public Collection<ProductDefinition> getIdForName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<ProductDefinition> refreshSock() {
        CsvFileReader();
        return products;
    }

    public Collection<ProductDefinition> getAll1() {
        return products;
    }
}
