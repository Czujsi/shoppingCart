package org.example.account;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserPasswordTest {
    private static final String EXAMPLE_PASSWORD = "wqdqui3214eIUQ    92QDIUHW";

    @Test
    void checkingIfUserPasswordWorksCorrectly() {
        UserPassword userPassword = new UserPassword(EXAMPLE_PASSWORD);

        assertThat(userPassword).isEqualTo(new UserPassword(EXAMPLE_PASSWORD));
    }
}