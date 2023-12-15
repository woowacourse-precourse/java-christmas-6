package christmas.domain.benefit.discount;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.BenefitType;

public class Discount implements Benefit {
    private final String benefitName;
    private final int BenefitPrice;

    public Discount(String benefitName, int benefitPrice) {
        this.benefitName = benefitName;
        BenefitPrice = benefitPrice;
    }

    @Override
    public String getBenefitName() {
        return benefitName;
    }

    @Override
    public int getBenefitPrice() {
        return BenefitPrice * -1;
    }

    @Override
    public boolean isTypeOf(BenefitType benefitType) {
        return BenefitType.DISCOUNT == benefitType;
    }
}
