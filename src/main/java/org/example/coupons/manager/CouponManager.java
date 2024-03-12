package org.example.coupons.manager;

import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.discount.type.DiscountType;
import org.springframework.stereotype.Component;

@Component
public interface CouponManager {
    boolean checkDiscountCode(String code);

    DiscountDefinition getCouponForCode(String code);

    void addDiscount(DiscountDefinition discountDefinition);

    void removeDiscount(String code);

    DiscountType[] getDiscountTypes();
}
