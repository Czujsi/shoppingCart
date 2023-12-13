package org.example.text_interface;

import org.example.logging_system.SimpleLogging;

import java.util.Arrays;
import java.util.Scanner;

public class Shop {
    ClientTextInterface clientTextInterface = new ClientTextInterface();
    SimpleLogging simpleLogging = new SimpleLogging();
    Scanner scanner = new Scanner(System.in);
    String[] choosingWords = {"log", "register", "shop", "exit"};


    public void run() {
        System.out.println("Welcome in our shop");
        System.out.println("If you would like to log-in type: log");
        System.out.println("To register account type: register");
        System.out.println("To go shopping type: shop");
        System.out.println("To exit type: exit");
        while (true) {
            scanner.reset();
            String option = scanner.nextLine().toLowerCase();
            if (choosing(option)) break;
        }
    }
    //region Choosing options in shop entering
    private boolean choosing(String option) {
        if (option.isEmpty()) {
            System.out.println("You need to type one of key words");
        }
        if (!Arrays.asList(choosingWords).contains(option)) {
            System.out.println("You need to type one of key words");
        }
        if (option.equals(choosingWords[3])) {
            System.out.println("Thank You for shopping, have a lovely day");
            return true;
        }
        if (option.equals(choosingWords[0])) {
            System.out.println("Logging system is currently in developing state");
            simpleLogging.logIn();
            return true;
        }
        if (option.equals(choosingWords[1])) {
            System.out.println("Registering system is currently in developing state");
        }
        if (option.equals(choosingWords[2])) {
            clientTextInterface.openCart();
            return true;
        }
        return false;
    }
    //endregion
}
