package org.example.coupons;

import lombok.AllArgsConstructor;
import org.example.coupons.map.parameters.CouponCode;

@AllArgsConstructor
public class CouponManager {
    public static boolean checkDiscountCode(CouponCode code) {
        return Discount.hasCoupon(code);
    }

}
