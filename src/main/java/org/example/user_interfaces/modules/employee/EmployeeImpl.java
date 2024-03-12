package org.example.user_interfaces.modules.employee;

import lombok.RequiredArgsConstructor;
import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.discount.type.DiscountType;
import org.example.coupons.discount.type.FlatPercentDiscount;
import org.example.coupons.manager.CouponManager;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.CreationDate;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;
import org.example.product.manager.ProductManager;
import org.example.user.UserInput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class EmployeeImpl implements Employee {

    private final ProductManager productManager;
    private final CouponManager couponManager;
    List<String> options = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void addToStock() {
        String productName = UserInput.getFieldInput("Type product name", scanner);
        String textPrice = UserInput.getFieldInput("Type product price", scanner);
        BigDecimal productPrice = new BigDecimal(textPrice);
        productManager.addProduct(new ProductDefinition(new Name(productName), new Price(Money.of(productPrice, Currency.PLN)), new CreationDate(LocalDate.now())));
        out.println("Product added!");
    }

    @Override
    public boolean checkIfExist(String input) {
        return productManager.exist(new ProductId(input));
    }

    @Override
    public void updateOnStock() {
        setOptions();
        printUpdateOptions();
        while (true) {
            String option = UserInput.getInput(scanner);
            if (!options.contains(option)) {
                out.println("Sorry wrong command, try again");
                printUpdateOptions();
            }
            if (option.equals("name")) {
                updateName();
                continue;
            }
            if (option.equals("price")) {
                updatePrice();
                continue;
            }
            if (option.equals("back")) {
                break;
            }
        }
    }

    private static void printUpdateOptions() {
        out.println("Type 'name' to update name");
        out.println("Type 'price' to update price");
        out.println("Type 'back' for going back");
    }

    private void updatePrice() {
        out.println("Type id of product that you want to update price: ");
        String id = UserInput.getInput(scanner);
        out.println(getFromStock(id));
        out.println("Type new price for that product: ");
        String textPrice = UserInput.getInput(scanner);
        BigDecimal newPrice = new BigDecimal(textPrice);
        productManager.updateProductPrice(new ProductId(id), Money.of(newPrice, Currency.PLN));
        out.println("Successfully updated!");
        out.println(getFromStock(id));
    }

    private void updateName() {
        out.println("Type product id: ");
        String id = UserInput.getInput(scanner);
        out.println(getFromStock(id));
        out.println("Type new product name: ");
        String newName = UserInput.getInput(scanner);
        productManager.updateProductName(new ProductId(id), newName);
        out.println("Successfully updated!");
        out.println(getFromStock(newName));
    }

    private ProductDefinition getFromStock(String id) {
        return productManager.getProductById(new ProductId(id)).orElseThrow();
    }

    @Override
    public void removeFromStock(String id) {
        if (!productManager.exist(new ProductId(id))) {
            System.out.println("Sorry, but we don't have that product on stock");
            return;
        }
        productManager.removeProduct(new ProductId(id));
    }

    @Override
    public void addFlatPercentDiscount() {
        System.out.println("Type discount code: ");
        String discountCode = UserInput.getInput(scanner);
        System.out.println("Type discount rate from 1 to 100: ");
        String value = UserInput.getInput(scanner);
        BigDecimal percent = new BigDecimal(value);
        couponManager.addDiscount(new DiscountDefinition(discountCode, Map.of(
                DiscountType.Cart, new FlatPercentDiscount(percent))));
    }

    @Override
    public void searchForItem(String id) {
        if (!checkIfExist(id)) {
            out.println("Sorry, but we don't have that product on stock");
            return;
        }
        out.println(productManager.getProductById(new ProductId(id)));
    }

    public void setOptions() {
        options.add("name");
        options.add("price");
        options.add("back");
    }
}