package org.example.text_interface;

import org.example.cart_components.Cart;
import org.example.cart_components.UserId;
import org.example.coupons.CouponManagerImpl;
import org.example.coupons.DiscountInMemoryRepository;
import org.example.product.ProductDefinition;

import java.util.Optional;
import java.util.Scanner;

import static java.util.Optional.*;

public class CartTextInterface {
    Scanner scanner = new Scanner(System.in);
    DiscountInMemoryRepository discountInMemoryRepository = new DiscountInMemoryRepository();
    CouponManagerImpl couponManager = new CouponManagerImpl(discountInMemoryRepository);
    UserId userId = new UserId(1L);
    Cart cart = new Cart(couponManager, userId);
    Products products = new Products();

    public void choseCartType() {
        System.out.println("If you are client type: Client, if you are shop type: Shop");
        String choseOption = scanner.nextLine();
        if (choseOption.equals("Client")) {
            openCart();
        } else if (choseOption.equals("Shop")) {
            openCartForShop();
        }
        scanner.close();
    }

    private void openCartForShop() {
        System.out.println("You are in cart with shop options");
    }

    private void openCart() {
        System.out.println("You are in our shop");
        System.out.println("""
                Here is a list of products you can buy in our shop: \r
                For adding any products type product name\r
                \s""");
        System.out.println("Milk " + products.milk.getPrice());
        System.out.println("Butter " + products.butter.getPrice());
        System.out.println("Apple " + products.apple.getPrice());
        System.out.println("To see the summary of your cart type: summary");
        choseProducts();
    }

    public void choseProducts() {
        do {
            scanner.reset();
            String option = scanner.nextLine().toLowerCase();
            if (option.equals("summary")) {
                break;
            }
            if (pickedProduct(option).isEmpty()) {
                System.out.println("We don't have that product");
                continue;
            }
            Optional<ProductDefinition> productDefinition = pickedProduct(option);
            productDefinition.ifPresent(definition -> cart.addItem(definition, 1));
            System.out.println(cart.overallSum());
        } while (true);
        printSummary();
        scanner.close();
    }

    private Optional<ProductDefinition> pickedProduct(String option) {
        return switch (option) {
            case "milk" -> ofNullable(products.milk);
            case "butter" -> ofNullable(products.butter);
            case "apple" -> ofNullable(products.apple);
            default -> empty();
        };
    }

    public void printSummary() {
        System.out.println("Summary of your purchases: ");
        cart.writeOutProducts();
        System.out.println("Price: " + cart.overallSum());
        System.out.println();
        System.out.println();
        System.out.println("Type: 'back' if you want to continue shopping");
        System.out.println("Type: 'options' if you want to go options ");
        System.out.println();
        String option = scanner.nextLine().toLowerCase();
        try {
            if (option.equals("back")) {
                choseProducts();
            }
            if (option.equals("option")) {
                summaryOptions("option");
            }
        } catch (Exception e) {
            System.out.println("You need to chose back or options");
        }


    }

    private void summaryOptions(String option) {
        switch (option) {
            case "option" -> {
                System.out.println("Type: 'buy' if You want to buy all products");
                System.out.println("Type: 'return' if you want to return any product");
                System.out.println("Type: 'discount' if you have any discount codes");
                System.out.println("Type: 'remove' if you want to remove discount");
            }
            case "buy" -> buyingSystem();
            case "return" -> deleteProduct();
        }
    }

    private void deleteProduct() {
        System.out.println("Type name of product you want to delete below, or type 'back' to return to summary options: ");
        String w = "option";
        do {
            String option = scanner.nextLine().toLowerCase();
            if (option.equals("back")) {
                break;
            }
            if(pickedProduct(option).isEmpty()){
                System.out.println("You don't have that product on your receipt");
                continue;
            }

            cart.removeItem(option);
        } while (true);
        summaryOptions(w);
    }

    private void buyingSystem() {
        System.out.println("You are in buying system");
    }
}
