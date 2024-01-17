package org.example.store;

import lombok.RequiredArgsConstructor;
import org.example.UserInput;
import org.example.coupons.CouponManager;
import org.example.coupons.DiscountDefinition;
import org.example.coupons.DiscountType;
import org.example.coupons.FlatPercentDiscount;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.ProductManager;
import org.example.product.components.DateForProduct;
import org.example.product.components.Price;
import org.example.product.components.ProductName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

@RequiredArgsConstructor
public class EmployeeImpl implements Employee {
    ProductManager productManager;
    CouponManager couponManager;
    Scanner scanner = new Scanner(System.in);

    public EmployeeImpl(ProductManager productManager, CouponManager couponManager) {
        this.productManager = productManager;
        this.couponManager = couponManager;
    }

    @Override
    public void addToStock() {
        String productName = UserInput.getInput(scanner);
        String textPrice = UserInput.getInput(scanner);
        BigDecimal productPrice = new BigDecimal(textPrice);
        productManager.addProduct(new ProductDefinition(new ProductName(productName), new Price(Money.of(productPrice, Currency.PLN)), new DateForProduct(LocalDate.now())));
    }

    @Override
    public boolean checkIfExist(String input) {
        return productManager.exist(input);
    }
    // TODO
    //  add instruction/option to edit name or price
    @Override
    public void updateOnStock() {
        while (true){
            String option = UserInput.getInput(scanner);
            if (option.equals("name")){
                updateName();
                continue;
            }
            if (option.equals("price")){
                updatePrice();
                continue;
            }
            if (option.equals("exit")){
                break;
            }
        }
    }

    private void wrongCommandInfo() {
        out.println("Sorry, bad command.");
    }

    private void updatePrice() {
        out.println("Type name of product that You want to update price: ");
        String productName = UserInput.getInput(scanner);
        out.println(getFromStock(productName));
        out.println("Type new price for that product: ");
        String textPrice = UserInput.getInput(scanner);
        BigDecimal newPrice = new BigDecimal(textPrice);
        productManager.updateProductPrice(productName, Money.of(newPrice, Currency.PLN));
        out.println("Successfully updated!");
        out.println(getFromStock(productName));
    }

    private void updateName() {
        out.println("Type product name: ");
        String oldName = UserInput.getInput(scanner);
        out.println(getFromStock(oldName));
        out.println("Type new product name: ");
        String newName = UserInput.getInput(scanner);
        productManager.updateProductName(oldName, newName);
        out.println("Successfully updated!");
        out.println(getFromStock(newName));
    }

    private ProductDefinition getFromStock(String oldName) {
        return productManager.getProductForName(oldName);
    }

    @Override
    public void removeFromStock(String input) {
        if (!productManager.exist(input)) {
            System.out.println("Sorry, but we don't have that product on stock");
            return;
        }
        productManager.removeProduct(input);
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
    public void searchForItem(String input) {
        if (!checkIfExist(input)) {
            out.println("Sorry, but we don't have that product on stock");
            return;
        }
        out.println(productManager.getProductForName(input));
    }
}