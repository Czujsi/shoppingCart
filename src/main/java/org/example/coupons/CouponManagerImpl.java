package org.example.coupons;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CouponManagerImpl implements CouponManager {
    private final DiscountRepository<String, DiscountDefinition> discountInMemoryDiscountRepository;
    @Override
    public boolean checkDiscountCode(String code) {
        return discountInMemoryDiscountRepository.exists(code);
    }

    @Override
    public DiscountDefinition getCouponForCode(String code) {
        return discountInMemoryDiscountRepository.get(code);
    }
    @Override
    public void addDiscount(DiscountDefinition discountDefinition) {
        discountInMemoryDiscountRepository.save(discountDefinition);
    }

    @Override
    public void removeDiscount(DiscountDefinition discountDefinition) {
        discountInMemoryDiscountRepository.delete(discountDefinition.getCode());
    }

}
