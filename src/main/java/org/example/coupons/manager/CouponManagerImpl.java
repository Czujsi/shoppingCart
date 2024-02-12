package org.example.coupons.manager;

import lombok.AllArgsConstructor;
import org.example.common.Repository;
import org.example.coupons.discount.DiscountDefinition;
import org.example.coupons.discount.type.DiscountType;

@AllArgsConstructor
public class CouponManagerImpl implements CouponManager {
    private final Repository<String, DiscountDefinition> discountInMemoryDiscountRepository;

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
    public void removeDiscount(String code) {
        discountInMemoryDiscountRepository.delete(code);
    }

    @Override
    public DiscountType[] getDiscountTypes() {
        return DiscountType.values();
    }
}
