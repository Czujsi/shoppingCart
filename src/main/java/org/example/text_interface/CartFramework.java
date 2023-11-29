package org.example.text_interface;

import java.util.Scanner;

public class CartFramework {
    Scanner scanner = new Scanner(System.in);


    public void choseCartType() {
        System.out.println("If you are client pres 1, if you are shop pres 2");
        int choseOption = scanner.nextInt();
        if (choseOption == 1) {
            openCartForClient();
        } else if (choseOption == 2) {
            openCartForShop();
        }
    }

    private void openCartForShop() {
        System.out.println("You are in cart with shop options");
    }

    private void openCartForClient() {
        System.out.println("You are in cart with client options");
    }
}
