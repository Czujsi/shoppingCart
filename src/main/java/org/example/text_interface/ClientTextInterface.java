package org.example.text_interface;

import org.example.cart_components.Cart;
import org.example.cart_components.UserId;
import org.example.coupons.CouponManager;
import org.example.coupons.CouponManagerImpl;
import org.example.coupons.DiscountInMemoryRepository;
import org.example.product.ProductInMemoryRepository;

import java.util.Scanner;

public class ClientTextInterface {
    private ProductInMemoryRepository productInMemoryRepository;
    private final Scanner scanner = new Scanner(System.in);
    private DiscountInMemoryRepository discountInMemoryRepository;
    private CouponManager couponManager;
    private final UserId userId = new UserId(1L);
    private final Cart cart = new Cart(couponManager, userId);

    public void openCart() {
        System.out.println("You are in our shop");
        System.out.println("""
                Here is a list of products you can buy in our shop: \r
                For adding any products type product name\r
                To get summary of Your purchases type: summary\r
                \s""");
        productInMemoryRepository.writeOutProducts();
        choseProducts();
    }

    public void choseProducts() {
        do {
            scanner.reset();
            String option = scanner.nextLine().toLowerCase();
            if (option.equals("summary")) {
                break;
            }
            pickedProduct(option);
            System.out.println(cart.overallSum());
        } while (true);
        printSummary();
        scanner.close();
    }

    private void pickedProduct(String option) {
        if (productInMemoryRepository.exists(option)) {
            cart.addItem(productInMemoryRepository.get(option), 1);
        }
        if (!productInMemoryRepository.exists(option)) {
            System.out.println("Sorry we don't have that product");
        }
    }

    public void printSummary() {
        System.out.println("Summary of your purchases: ");
        cart.writeOutProducts();
        System.out.println("Price: " + cart.overallSum());
        System.out.println();
        System.out.println();
        System.out.println("Type: 'back' if you want to continue shopping");
        System.out.println("Type: 'next' if you want to go to purchasing and discount section");
        System.out.println();
        String option = scanner.nextLine().toLowerCase();
        if (option.equals("back")) {
            choseProducts();
        }
        if (option.equals("next")) {
            summaryOptions();
        }
    }

    private void summaryOptions() {
        System.out.println("Type: 'buy' if You want to buy all products");
        System.out.println("Type: 'return' if you want to return any product");
        System.out.println("Type: 'discount' if you have any discount codes");
        System.out.println("Type: 'remove' if you want to remove discount");
        System.out.println("Type: 'back' if you want back to summary");

        do {
            scanner.reset();
            String option = scanner.nextLine().toLowerCase();
            if (option.equals("back")) {
                printSummary();
                break;
            }
            switch (option) {
                case "buy" -> buyingSystem();
                case "return" -> deleteProduct();
            }
        } while (true);
    }

    private void deleteProduct() {
        System.out.println("Type name of product you want to delete below, or type 'back' to return to summary options: ");
        do {
            scanner.reset();
            String option = scanner.nextLine().toLowerCase();
            if (option.equals("back")) {
                break;
            }
            if (!cart.has(option)) {
                System.out.println("You don't have that product on your receipt");
                continue;
            }
            if (cart.has(option)) {
                cart.removeItem(option);
                System.out.println("Product: " + option + " have been removed from your cart");
            }
        } while (true);
        summaryOptions();
    }

    private void buyingSystem() {
        System.out.println("You are in buying system");
    }
}
