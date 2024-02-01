package org.example.user_interfaces.modules;

import lombok.RequiredArgsConstructor;
import org.example.store.employee.Employee;
import org.example.user.UserInput;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@RequiredArgsConstructor
public class StockOperations {
    private final Employee employee;
    private final Scanner scanner = new Scanner(System.in);
    private final List<String> options = new ArrayList<>();

    public void option(){
        checkOption();
        printingOptions();
        while (true) {
            String input = UserInput.getInput(scanner);
            if(!options.contains(input)){
                System.out.println("Wrong command, try again");
                continue;
            }
            if (input.equals("back")) {
                break;
            }
            if (input.equals("add")) {
                employee.addToStock();
                continue;
            }
            if (input.equals("delete")){
                employee.removeFromStock(input);
                continue;
            }
            if (input.equals("update")){
                employee.updateOnStock();
                continue;
            }
            if (input.equals("search")) {
                System.out.println("Type product name that You are searching for");
                employee.searchForItem(input);
            }
        }
    }

    private static void printingOptions() {
        System.out.println("Adding new item to stock, type: add");
        System.out.println("Deleting item from stock, type: delete");
        System.out.println("Searching for item on stock, type: search");
        System.out.println("Updating item on stock, type: update");
        System.out.println("For going back, type: back");
    }

    private void checkOption(){
        options.add("back");
        options.add("add");
        options.add("delete");
        options.add("update");
        options.add("search");
    }
}
