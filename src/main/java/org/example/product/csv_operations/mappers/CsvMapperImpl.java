package org.example.product.csv_operations.mappers;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.example.currency_exchange_money.Currency.PLN;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class CsvMapperImpl implements Mapper<ProductDefinition, String> {

    @Override
    public String deserialize(ProductDefinition type) {
        return productDefinitionToString(type);
    }

    @Override
    public ProductDefinition serialize(String representation) {
        String[] values = representation.split(";");

        if (values.length != 4) {
            throw new IllegalArgumentException("Invalid product string: " + representation);
        }
        String name = values[0];
        BigDecimal price = new BigDecimal(values[1]);
        String creationDate = values[2];
        String productId = values[3];

        LocalDate date = LocalDate.parse(creationDate);


        return new ProductDefinition(new Name(name), new Price(Money.of(price, PLN)), date, new ProductId(productId));
    }

    private static String productDefinitionToString(ProductDefinition productDefinition) {
        return String.format("%s;%s;%s;%s",
                productDefinition.getName().getValue(),
                productDefinition.getPrice().getAmount().toString(),
                productDefinition.getCreationDate(),
                productDefinition.getProductId().getValue());
    }
}
