package org.example.account;

import org.junit.jupiter.api.Test;

class AccountTest {
    @Test
    void creatingAccountTest() {
        Account account = new Account(new UserLogin("tomek1231"), new UserPassword("password123"), new UserName("Tomasz"));

    }
}