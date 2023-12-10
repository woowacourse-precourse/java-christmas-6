package christmas.model.reciept;

import christmas.model.promotion.Promotion;
import christmas.model.promotion.PromotionType;
import java.util.EnumMap;
import java.util.Map.Entry;

public class BenefitResult {
    private final EnumMap<Promotion, Double> benefitRecords;

    public BenefitResult(EnumMap<Promotion, Double> benefitRecords) {
        this.benefitRecords = benefitRecords;
    }

    public static BenefitResult from(EnumMap<Promotion, Double> benefitRecords) {
        return new BenefitResult(benefitRecords);
    }

    public double getTotalDiscountedAmount() {
        return benefitRecords.entrySet()
                .stream()
                .filter(benefit -> benefit.getKey().getPromotionType().equals(PromotionType.DISCOUNT))
                .mapToDouble(Entry::getValue)
                .sum();
    }

    public double getTotalBenefitAmount() {
        return benefitRecords.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public EnumMap<Promotion, Double> getBenefitRecords() {
        return benefitRecords;
    }
}
