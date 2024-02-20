package org.example.account;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
    @Test
    void creatingAccountTest() {
        Account account = new Account(new UserLogin("tomek1231"), new UserPassword("password123"), new UserName("Tomasz"));

        assertThat(account.getUserId()).isEqualTo(account.getUserId());
        assertThat(account.getUserName()).isEqualTo(new UserName("Tomasz"));
    }
}