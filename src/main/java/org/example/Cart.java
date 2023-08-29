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
        products.put(new Product(productName), productQuantity + oldAmount);

    }

    public void removeItem(String productName) {
        products.entrySet().removeIf(product -> product.getKey().getProductName().equals(productName));
    }


    public boolean has(String item) {
        return products.containsKey(new Product(item));
    }

    public int quantityOf(String item) {
        Integer quantity = products.get(new Product(item));
        return quantity == null ? 0 : quantity;
    }

    public void removeQuantity(String productName, int productQuantity) {

        if (productQuantity <= 0) {
            throw new RuntimeException("You cannot remove negative value of products");
        }
        int oldAmount = quantityOf(productName);
        products.put(new Product(productName), oldAmount - productQuantity);
        if (oldAmount < productQuantity) {
            throw new RuntimeException("You cannot remove more product than you have in cart");
        }

    }
}
