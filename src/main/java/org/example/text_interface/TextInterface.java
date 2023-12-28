package org.example.text_interface;

import org.example.UserInput;
import org.example.store.Store;
import org.example.user_interface.UserInterface;

import java.util.Scanner;

public class TextInterface extends UserInterface {
    Store store;
    Scanner scanner = new Scanner(System.in);

    public TextInterface(Store store) {
        super();
        this.store = store;
    }

    public void run() {
        showOptions();
    }

    private void showOptions() {
        System.out.println("Welcome in our shop user");
        System.out.println();
        System.out.println("If you want to log-in, type: log");
        System.out.println("If you want to register, type: register");
        System.out.println("If you want to shop without account, type: shop");
        System.out.println("If you want to exit, type: exit");
        String input = UserInput.getInput(scanner);
        if (input.equals("log")) {
            System.out.println("Logging system is currently unapproachable, sorry for trouble");
            showOptions();
        }
        if (input.equals("register")) {
            System.out.println("Register system is currently unapproachable, sorry for trouble");
            showOptions();
        }
        if (input.equals("shop")) {
            choosingItems();
        }
        if (input.equals("exit")) {
            System.out.println("Thank you for visiting, have a nice day");
        }
        if (input.equals("add")) {
            addItemsToStock();
            showOptions();
        }
    }

    private void choosingItems() {
        System.out.println("Here are items that You can buy in our shop: ");
        store.printAllItemsFromStock();
        System.out.println("For adding products to your cart type product name");
        System.out.println("To exit type: 'exit'");
        System.out.println("For summary type: 'summary'");
        while (true) {
            String input = UserInput.getInput(scanner);
            if (input.equals("exit")) {
                System.out.println("Thank You for visiting, have a nice day");
                break;
            }
            if (input.equals("summary")) {
                store.printSummary();
                summaryOptions();
                break;
            }
            if (input.equals("search")) {
                System.out.println("Type product name that You searching for");
                store.searchForItem(input);
            }
            if (!store.checkIfExists(input)) {
                System.out.println("Sorry we don't have that item");
                continue;
            }
            if (store.checkIfExists(input)) {
                store.addItemToCart(input);
            }
        }
    }

    private void summaryOptions() {
        System.out.println("Summary options: ");
        System.out.println("For removing any product type: 'remove'");
        System.out.println("For summary of Your products type: 'sum'");
        System.out.println("For going back to shopping type: 'back'");
        System.out.println("For paying type: 'pay'");
    }




    //just for testing
    private void addItemsToStock() {
        System.out.println("Type product name, and price");
        store.addItemToStock();
    }
}
