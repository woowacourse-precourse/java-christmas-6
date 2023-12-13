package christmas.domain.benefit;

public class Benefit {
    private final BenefitType benefitType;
    private final String benefitName;
    private final int BenefitPrice;

    public Benefit(BenefitType benefitType, String benefitName, int benefitPrice) {
        this.benefitType = benefitType;
        this.benefitName = benefitName;
        BenefitPrice = benefitPrice;
    }

    public String getBenefitName() {
        return benefitName;
    }

    public int getBenefitPrice() {
        return BenefitPrice * -1;
    }

    public boolean isTypeOf(BenefitType benefitType) {
        return this.benefitType == benefitType;
    }
}
