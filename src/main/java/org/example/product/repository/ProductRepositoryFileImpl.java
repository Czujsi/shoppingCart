package org.example.product.repository;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;
import org.example.product.converters.CsvConverter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
@RequiredArgsConstructor
@EqualsAndHashCode
public class ProductRepositoryFileImpl implements ProductRepository<ProductId, ProductDefinition> {

    private final CsvConverter csvConverter;
    Set<ProductDefinition> products = new HashSet<>();
    File file = new File("C:\\Users\\oscar\\Desktop\\Programming\\projects\\shoppingCart\\src\\main\\resources\\test.csv");

    @Override
    public void save(ProductDefinition productDefinition) {
        if (exists(productDefinition.getProductId())) {
            throw new IllegalArgumentException("Product already exists");
        }

        products.add(productDefinition);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(convertToCSV(productDefinition));
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String convertToCSV(ProductDefinition productDefinition) {
        return String.format("%s;%s;%s;%s",
                productDefinition.getName().getValue(),
                productDefinition.getPrice().getAmount().toString(),
                productDefinition.getCreationDate(),
                productDefinition.getProductId().getValue());
    }

    @Override
    public void delete(ProductId productId) {
        if (!exists(productId)) {
            throw new IllegalArgumentException("Product does not exists");
        }

        products.removeIf(productDefinition -> productDefinition.getProductId().getValue().equals(productId.getValue()));

        removeFromFile(productId);
    }

    private void removeFromFile(ProductId productId) {
        try {

            File inputFile = new File("src/main/resources/test.csv");
            File tempFile = new File("src/main/resources/temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] columns = currentLine.split(";");
                if (columns.length >= 3 && !columns[3].equals(productId.getValue().toString())) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename the temporary file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(ProductId productId) {
        return products.stream().anyMatch(p -> p.getProductId().getValue().equals(productId.getValue()));
    }

    @Override
    public Optional<ProductDefinition> findById(ProductId productId) {
        return products.stream().filter(v -> v.getProductId().equals(productId)).findFirst();
    }

    @Override
    public Collection<ProductDefinition> getAll() {
        return products;
    }

    private void csvFileReader() {
        List<ProductDefinition> productDefinitions = csvConverter.convertFromFile(file.getPath());
        products.addAll(productDefinitions);
    }

    @Override
    public List<String> getIdForName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<ProductDefinition> refreshStock() {
        csvFileReader();
        return products;
    }

    public Collection<ProductDefinition> getAll1() {
        return products;
    }
}
