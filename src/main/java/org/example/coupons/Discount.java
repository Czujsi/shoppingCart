package org.example.coupons;

import org.example.coupons.map.parameters.CodeValue;
import org.example.coupons.map.parameters.CouponCode;

import java.util.HashMap;

public class Discount {

    private static final HashMap<CouponCode, CodeValue> coupons = new HashMap<>();

    protected static boolean hasCoupon(String code) {

        return coupons.containsKey(new CouponCode(code));
    }

    public static CodeValue getValueForCode(String code) {

        return coupons.get(code);
    }

    public void addDiscount(String discountCode) {

        coupons.put(new CouponCode(discountCode), new CodeValue());

    }
}
