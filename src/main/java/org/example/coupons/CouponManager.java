package org.example.coupons;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CouponManager {
    public static boolean checkDiscountCode(String code) {
        return Discount.hasCoupon(code);
    }

}
