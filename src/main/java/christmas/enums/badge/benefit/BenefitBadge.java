package christmas.enums.badge.benefit;

import christmas.enums.badge.Badge;

public enum BenefitBadge implements Badge {
    SANTA("산타", 20_000, Integer.MAX_VALUE), TREE("트리", 10_000, 19_999), STAR("별", 5_000, 9_999), NONE("없음", 0, 4_999);

    private final String name;
    private final Integer minimumBenefit;
    private final Integer maxBenefit;

    BenefitBadge(String name, Integer minimumBenefit, Integer maxBenefit) {
        this.name = name;
        this.minimumBenefit = minimumBenefit;
        this.maxBenefit = maxBenefit;
    }

    public static BenefitBadge determineBadge(int totalBenefitAmount) {
        for (BenefitBadge badge : BenefitBadge.values()) {
            if (totalBenefitAmount >= badge.minimumBenefit && totalBenefitAmount <= badge.maxBenefit) {
                return badge;
            }
        }
        return NONE;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getAmount() {
        return minimumBenefit;
    }
}
