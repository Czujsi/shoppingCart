package org.example.product.components;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CreationDateTest {
    private static final LocalDate CURRENT_DAY = LocalDate.now();

    @Test
    void name() {
        CreationDate creationDate = new CreationDate(LocalDate.now());

        Assertions.assertThat(creationDate.localDate).isEqualTo(CURRENT_DAY);
    }
}