package org.example.coupons;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CouponManagerImpl implements CouponManager {
    private final Repository<String, DiscountDefinition> discountInMemoryRepository;
    @Override
    public boolean checkDiscountCode(String code) {
        return discountInMemoryRepository.exists(code);
    }

    @Override
    public DiscountDefinition getCouponForCode(String code) {
        return discountInMemoryRepository.get(code);
    }
    @Override
    public void addDiscount(DiscountDefinition discountDefinition) {
        discountInMemoryRepository.save(discountDefinition);
    }

    @Override
    public void removeDiscount(DiscountDefinition discountDefinition) {
        discountInMemoryRepository.delete(discountDefinition.getCode());
    }

}
