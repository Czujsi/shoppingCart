package org.example.product.components;


import java.time.LocalDate;

public class DateForProduct {
    public LocalDate localDate;
    public DateForProduct(LocalDate localDate){
        this.localDate = localDate;
    }
    public String toString() {
        return localDate.toString();
    }
}
