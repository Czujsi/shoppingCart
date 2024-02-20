package org.example.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserNameTest {

    @Test
    void testingIfNameIsCaseSensitive() {
        UserName userName = new UserName("KajTek");

        Assertions.assertThat(userName).isEqualTo(new UserName("KajTek"));
    }

    @Test
    void testingIfNameCanHaveBlankSpaces() {
        UserName userName = new UserName("Kaj Tek");

        Assertions.assertThat(userName).isEqualTo(new UserName("KajTek"));
    }

    @Test
    void testingIfNameCanHaveBlankSpacesAtStartOfTheLine() {
        UserName userName = new UserName("  Kaj Tek");

        Assertions.assertThat(userName).isEqualTo(new UserName("KajTek"));
    }

    @Test
    void testingIfNameCanHaveBlankSpacesAtEndOfTheLine() {
        UserName userName = new UserName("  Kaj Tek  ");

        Assertions.assertThat(userName).isEqualTo(new UserName("KajTek"));
    }

    @Test
    void testingIfNameCanHaveEnterAtEndOfTheLine() {
        UserName userName = new UserName("  Kaj \n Tek  ");

        Assertions.assertThat(userName).isEqualTo(new UserName("KajTek"));
    }
}