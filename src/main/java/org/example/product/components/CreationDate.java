package org.example.product.components;


import java.time.LocalDate;

public class CreationDate {
    public LocalDate localDate;

    public CreationDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String toString() {
        return localDate.toString();
    }

    public String getValue() {
        return localDate.toString();
    }
}
