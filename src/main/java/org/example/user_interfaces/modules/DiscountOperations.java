package org.example.user_interfaces.modules;

import lombok.RequiredArgsConstructor;
import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.discount.type.DiscountType;
import org.example.coupons.discount.type.FlatPercentDiscount;
import org.example.coupons.discount.type.FreeTransportDiscount;
import org.example.coupons.discount.type.SimpleAmountDiscount;
import org.example.coupons.manager.CouponManager;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.user.UserInput;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.text.MessageFormat.format;
@RequiredArgsConstructor
public class DiscountOperations {
    private final CouponManager couponManager;

    private final Scanner scanner = new Scanner(System.in);
    public void options(){
        while (true){
            System.out.println("For help type help");
            String input = UserInput.getInput("discount operations", scanner);
            if (input.equals("back")){
                break;
            } else if (input.equals("add")) {
                addDiscount();
            } else if (input.equals("delete")) {
                deleteDiscount();
            }
            else if (input.equals("help")) {
                printOptions();
            }
        }
    }

    private void deleteDiscount() {
        String code = UserInput.getInput("Enter discount code to delete discount", ":", scanner);
        couponManager.removeDiscount(code);
        System.out.println("Discount removed!");
    }

    private void printOptions() {
        System.out.println("Options: ");
        System.out.println("\t\u001B[1madd\u001B[0m     -> for adding new discounts");
        System.out.println("\t\u001B[1mback\u001B[0m    -> for going back");
    }

    private List<DiscountType> getDiscountTypes(){
        return List.of(couponManager.getDiscountTypes());
    }
    private String getString(int i, DiscountType discountType) {
        return format("{0} -> Discount type: {1}", i, discountType.name());
    }
    private int getNumber(String userInput) {
        return Integer.parseInt(userInput);
    }
    private DiscountType getType(String userInput) {
        return getDiscountTypes().get(getNumber(userInput));
    }

    private void addDiscount() {
        System.out.println("Chose discount type:");
        for (int i = 0; i < getDiscountTypes().size(); i++) {
            System.out.println(getString(i, getDiscountTypes().get(i)));
        }
        String number = UserInput.getInput("Type number", ":", scanner);

        System.out.println("Type discount code: ");

        String discountCode = UserInput.getInput("discount code", ":", scanner);


        if (getType(number).equals(DiscountType.Product)){
            String textPrice = UserInput.getInput("Enter the discount percent amount", ":", scanner);
            BigDecimal multiplier = new BigDecimal(textPrice);
            couponManager.addDiscount(new DiscountDefinition(discountCode, Map.of(
                    getType(number), new FlatPercentDiscount(multiplier))));
        } else if (getType(number).equals(DiscountType.Cart)) {
            String textPrice = UserInput.getInput("Enter the discount amount", ":", scanner);
            BigDecimal amount = new BigDecimal(textPrice);
            couponManager.addDiscount(new DiscountDefinition(discountCode, Map.of(
                    getType(number), new SimpleAmountDiscount(Money.of(amount, Currency.PLN)))));
        } else if (getType(number).equals(DiscountType.Transport)) {
            couponManager.addDiscount(new DiscountDefinition(discountCode, Map.of(
                    getType(number), new FreeTransportDiscount(discountCode, 10.00)
            )));
        }
        System.out.println("Discount with type: " + getType(number) + ", and code: " + discountCode + " created");
    }
}
