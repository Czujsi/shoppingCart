package org.example.product.repository;

import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.CreationDate;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProductRepositoryFileImpl implements ProductRepository<ProductId, ProductDefinition> {
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

    public Collection<ProductDefinition> refreshStock(){
        CsvFileReader();
        return products;
    }
    private void CsvFileReader() {
        BufferedReader reader = null;
        String line;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] column = line.split(";");
                if (column.length >= 3) {
                    String name = column[0];

                    BigDecimal price = new BigDecimal(column[1]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(column[2], formatter);

                    String id = column[3];

                    ProductDefinition productDefinition = new ProductDefinition(new Name(name), new Price(Money.of(price, Currency.PLN)), new CreationDate(date), new ProductId(id));

                    products.add(productDefinition);
                } else {
                    throw new IllegalArgumentException("Invalid line format: " + line);
                }


            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(reader).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
