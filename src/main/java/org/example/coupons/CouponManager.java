package org.example.coupons;

public interface CouponManager {
    boolean checkDiscountCode(String code);

    DiscountDefinition getCouponForCode(String code);

    void addDiscount(DiscountDefinition discountDefinition);

    void removeDiscount(DiscountDefinition discountDefinition);


}
