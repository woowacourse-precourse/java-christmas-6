package christmas.domain.benefit;

import christmas.domain.Orders;
import christmas.domain.PromotionDate;
import java.util.Collections;
import java.util.List;

public class Benefits {
    private final List<Benefit> benefits;

    public Benefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public static Benefits from(PromotionDate visitDay, Orders orders){
        return new Benefits(BenefitFactory.allPossibleBenefit(visitDay, orders));
    }

    public List<Benefit> getBenefits() {
        return Collections.unmodifiableList(benefits);
    }

    public boolean hasNoBenefits(){
        return benefits.isEmpty();
    }

    public int getTotalBenefitPrice(){
        return benefits.stream()
                .mapToInt(Benefit::getBenefitPrice)
                .sum();
    }

    public int calcDiscountPrice(){
        return benefits.stream()
                .filter(benefit -> benefit.isTypeOf(BenefitType.DISCOUNT))
                .mapToInt(Benefit::getBenefitPrice)
                .sum();
    }
}
