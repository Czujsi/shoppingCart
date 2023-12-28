package org.example.store;

import lombok.RequiredArgsConstructor;
import org.example.UserInput;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.ProductManager;
import org.example.product.components.Price;
import org.example.product.components.ProductName;

import java.math.BigDecimal;
import java.util.Scanner;

@RequiredArgsConstructor
public class EmployeeImpl implements Employee {
    ProductManager productManager;
    Scanner scanner = new Scanner(System.in);

    public EmployeeImpl(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void addToStock() {
        String productName = UserInput.getInput(scanner);
        String textPrice = UserInput.getInput(scanner);
        BigDecimal productPrice = new BigDecimal(textPrice);
        productManager.addProduct(new ProductDefinition(new ProductName(productName), new Price(Money.of(productPrice, Currency.PLN))));
    }

    @Override
    public boolean checkIfExist(String input) {
        return productManager.exist(input);
    }

    @Override
    public void updateOnStock() {
        String oldName = UserInput.getInput(scanner);
        String productName = UserInput.getInput(scanner);
        String textPrice = UserInput.getInput(scanner);
        BigDecimal productPrice = new BigDecimal(textPrice);
        productManager.editProduct(oldName, new ProductDefinition(new ProductName(productName), new Price(Money.of(productPrice, Currency.PLN))));
    }

    @Override
    public void removeFromStock() {
        String productName = UserInput.getInput(scanner);
        if (!productManager.exist(productName)) {
            System.out.println("Sorry, but we don't have that product on stock");
            return;
        }
        productManager.removeProduct(productName);
    }
}
