package org.example;


import lombok.ToString;
import org.example.calculator.Calculate;
import org.example.coupons.CouponManager;
import org.example.coupons.Discount;
import org.example.coupons.map.parameters.CodeValue;
import org.example.coupons.map.parameters.CouponCode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@ToString
public class Cart {
    private static final Map<Product, Integer> products = new HashMap<>();

    Calculate calculate;

    public static Map<Product, Integer> getProducts() {
        return products;
    }


    public void addItem(String productName, int productQuantity) {
        if (productQuantity == 0) {
            return;
        }
        if (productQuantity < 0) {
            throw new RuntimeException("You cannot add negative value of product");
        }
        int oldAmount = quantityOf(productName);
        products.put(new Product(productName, new Price(2.50)), productQuantity + oldAmount);

    }

    public void removeItem(String productName) {
        products.entrySet().removeIf(product -> product.getKey().getProductName().equals(new ProductName(productName)));
    }


    public boolean has(String productName) {
        return products.containsKey(new Product(productName, new Price(2.50)));
    }

    public int quantityOf(String item) {
        Integer quantity = products.get(new Product(item, new Price(2.50)));
        return quantity == null ? 0 : quantity;
    }


    public void removeQuantity(String productName, int productQuantity) {

        if (productQuantity <= 0) {
            throw new RuntimeException("You cannot remove negative value of products");
        }
        int oldAmount = quantityOf(productName);
        products.put(new Product(productName, new Price(2.50)), oldAmount - productQuantity);
        if (oldAmount < productQuantity) {
            throw new RuntimeException("You cannot remove quantity of product that is not in your cart");
        }


    }

    public void isDiscountValid(CouponCode code) {
        //sprawdzamy czy taki kupon jaki użytkowanik nam dał istanieje w bazie kuponów

        if (CouponManager.checkDiscountCode(code)) {
            //jeżeli istnieje to pobieramy wartość dla kuponu
            CodeValue valueForCode = Discount.getValueForCode(code);
            //następnie wysyłamy wartość kuponu do metody liczącej w klasie Calculate
            Stream.of(valueForCode).forEach(calculate::getValueToOperateForDiscount);
            //następnie pobierz nowe ceny produktów


        } else {
            System.out.println("Coupon is not valid or correct");
        }
    }


}
