package christmas.dto;

import java.util.Map;

public class BenefitDto {

    private final Map<String, Integer> benefit;
    private final int totalDiscount;
    private final int discountedTotalPrice;

    public BenefitDto(final Map<String, Integer> benefit,
                      final int totalDiscount,
                      final int discountedTotalPrice) {
        this.benefit = benefit;
        this.totalDiscount = totalDiscount;
        this.discountedTotalPrice = discountedTotalPrice;
    }

    public Map<String, Integer> getBenefit() {
        return benefit;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }
    public int getDiscountedTotalPrice() {
        return discountedTotalPrice;
    }
}
