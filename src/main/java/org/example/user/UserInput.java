package org.example.user;

import java.util.Scanner;

public class UserInput {
    public static String getInput(Scanner scanner) {
        return getInput("", scanner);
    }

    public static String getInput(String prompt, Scanner scanner) {
        return getInput(prompt, ">", scanner);
    }

    public static String getFieldInput(String prompt, Scanner scanner) {
        return getInput(prompt, ":", scanner);
    }

    public static String getInput(String prompt, String symbol, Scanner scanner) {
        System.out.print(prompt + symbol + " ");
        String input = scanner.nextLine();
        return input.toLowerCase().replace(" ", "");
    }
}
