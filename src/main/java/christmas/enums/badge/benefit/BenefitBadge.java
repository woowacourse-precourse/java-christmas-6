package christmas.enums.badge.benefit;

import christmas.enums.badge.Badge;

public enum BenefitBadge implements Badge {
    SANTA("산타",20_000)
    , TREE("트리",10_000)
    , STAR("별",5_000)
    , NONE("없음",0);

    private final String name;
    private final Integer benefitCondition;

    BenefitBadge(String name, Integer benefitCondition) {
        this.name = name;
        this.benefitCondition = benefitCondition;
    }

    public static BenefitBadge determineBadge(Integer totalBenefitAmount) {
        for (BenefitBadge badge : values()) {
            if (totalBenefitAmount >= badge.benefitCondition) {
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
        return benefitCondition;
    }
}
