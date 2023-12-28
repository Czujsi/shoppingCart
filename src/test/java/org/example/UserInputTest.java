package org.example;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {
    Scanner scanner = new Scanner(System.in);
    @Test
    void name() {
        UserInput.getInput(scanner);


    }
}