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
import org.example.product.components.ProductId;
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
        return productManager.exist(new ProductId(input));
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
}