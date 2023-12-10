package christmas.model.reciept;

import christmas.model.menu.Menu;
import christmas.model.promotion.Promotion;
import java.util.EnumMap;

public class PromotionResult {
    private final BenefitResult benefitResult;
    private final FreebieResult freebieResult;

    private PromotionResult(BenefitResult benefitResult, FreebieResult freebieResult) {
        this.benefitResult = benefitResult;
        this.freebieResult = freebieResult;
    }

    public static PromotionResult from(BenefitResult benefitResult, FreebieResult freebieResult) {
        return new PromotionResult(benefitResult, freebieResult);
    }

    public double getTotalDiscountedAmount() {
        return benefitResult.getTotalDiscountedAmount();
    }

    public double getTotalBenefitAmount() {
        return benefitResult.getTotalBenefitAmount();
    }

    public EnumMap<Menu, Integer> getFreebies() {
        return freebieResult.getFreebies();
    }

    public EnumMap<Promotion, Double> getBenefitRecords() {
        return benefitResult.getBenefitRecords();
    }
}
