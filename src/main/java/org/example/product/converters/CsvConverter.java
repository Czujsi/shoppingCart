package org.example.product.converters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvConverter implements FileConverter {
    private static final Logger logger = LoggerFactory.getLogger(CsvConverter.class);
    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int DATE_INDEX = 2;
    private static final int ID_INDEX = 3;
    private final List<ProductDefinition> products = new ArrayList<>();

    public List<ProductDefinition> convertedProducts() {
        return products;
    }

    @Override
    public List<ProductDefinition> convertFromFile(String filePath) {
        products.clear();
        List<ProductDefinition> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");
                if (columns.length >= 4) {
                    try {
                        String name = columns[NAME_INDEX];
                        BigDecimal price = new BigDecimal(columns[PRICE_INDEX]);
                        LocalDate date = LocalDate.parse(columns[DATE_INDEX], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        String id = columns[ID_INDEX];

                        ProductDefinition productDefinition = new ProductDefinition(
                                new Name(name),
                                new Price(Money.of(price, Currency.PLN)),
                                date,
                                new ProductId(id)
                        );
                        products.add(productDefinition);
                    } catch (DateTimeParseException | NumberFormatException e) {
                        logger.warn("Error parsing line: " + line, e);
                    }
                } else {
                    logger.warn("Invalid line format: {}", line);
                }
            }
        } catch (IOException e) {
            logger.error("Error reading file: {}", filePath, e);
        }
        return products;
    }

    @Override
    public void convertToFile() {

    }
}
