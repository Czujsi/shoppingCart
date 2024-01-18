package org.example.coupons.manager;

import org.example.coupons.discount.DiscountDefinition;

public interface CouponManager {
    boolean checkDiscountCode(String code);

    DiscountDefinition getCouponForCode(String code);

    void addDiscount(DiscountDefinition discountDefinition);

    void removeDiscount(DiscountDefinition discountDefinition);

}
