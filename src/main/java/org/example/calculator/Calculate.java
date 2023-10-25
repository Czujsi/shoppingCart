package org.example.calculator;

import org.example.Cart;
import org.example.Product;
import org.example.coupons.map.parameters.CodeValue;

import java.util.Iterator;
import java.util.Map;

public class Calculate {
    Iterator<Map.Entry<Product, Integer>> iterator = Cart.getProducts().entrySet().iterator();


    public void getValueToOperateForDiscount(CodeValue codeValue) {
        while (iterator.hasNext()) {
            recalculatePriceWithCoupon();
        }
    }

    public void recalculatePriceWithCoupon() {

    }
}
