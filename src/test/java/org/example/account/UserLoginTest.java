package org.example.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserLoginTest {
    @Test
    void testingIfLoginIsCaseSensitive() {
        UserLogin userLogin = new UserLogin("KajTek");

        Assertions.assertThat(userLogin).isEqualTo(new UserLogin("KajTek"));
    }

    @Test
    void testingIfLoginCanHaveBlankSpaces() {
        UserLogin userLogin = new UserLogin("Kaj Tek");

        Assertions.assertThat(userLogin).isEqualTo(new UserLogin("KajTek"));
    }

    @Test
    void testingIfLoginCanHaveBlankSpacesAtStartOfTheLine() {
        UserLogin userLogin = new UserLogin("  Kaj Tek");

        Assertions.assertThat(userLogin).isEqualTo(new UserLogin("KajTek"));
    }

    @Test
    void testingIfLoginCanHaveBlankSpacesAtEndOfTheLine() {
        UserLogin userLogin = new UserLogin("  Kaj Tek  ");

        Assertions.assertThat(userLogin).isEqualTo(new UserLogin("KajTek"));
    }

    @Test
    void testingIfLoginCanHaveEnterAtEndOfTheLine() {
        UserLogin userLogin = new UserLogin("  Kaj \n Tek  ");

        Assertions.assertThat(userLogin).isEqualTo(new UserLogin("KajTek"));
    }
}