package christmas.domain.benefit;

public interface Benefit {

    String getBenefitName();

    int getBenefitPrice();

    boolean isTypeOf(BenefitType benefitType);
}
