package org.example;


import lombok.ToString;

import java.util.HashMap;

import java.util.Map;

@ToString
public class Cart {
    private final Map<Product, Integer> products = new HashMap<>();


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
        products.entrySet().removeIf(product -> product.getKey().getProductName().equals(productName));
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

}
