package org.example.product.converters;

import lombok.RequiredArgsConstructor;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.CreationDate;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CsvConverter implements FileConverter {
    ProductDefinition productDefinition;
    private final List<ProductDefinition> products = new ArrayList<>();

    @Override
    public List<ProductDefinition> convertFromFile(String filePath) {
        BufferedReader reader = null;
        String line;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                String[] column = line.split(";");
                if (column.length >= 3) {
                    String name = column[0];

                    BigDecimal price = new BigDecimal(column[1]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(column[2], formatter);

                    String id = column[3];

                    productDefinition = new ProductDefinition(new Name(name), new Price(Money.of(price, Currency.PLN)), new CreationDate(date), new ProductId(id));

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
        return products;
    }

    public List<ProductDefinition> convertedProducts() {
        return products;
    }

    @Override
    public void convertToFile() {

    }
}
