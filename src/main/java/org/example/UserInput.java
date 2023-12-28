package org.example;

import java.util.Scanner;

public class UserInput {
    public static String getInput(Scanner scanner){
        String input = scanner.nextLine();
        return input.toLowerCase().replace(" ", "");
    }
}
