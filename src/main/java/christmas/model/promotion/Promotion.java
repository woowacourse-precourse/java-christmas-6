package christmas.model.promotion;

public enum Promotion {
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인", PromotionType.DISCOUNT),
    WEEKDAY_DISCOUNT("평일 할인", PromotionType.DISCOUNT),
    WEEKEND_DISCOUNT("주말 할인", PromotionType.DISCOUNT),
    SPECIAL_DAY_DISCOUNT("특별 할인", PromotionType.DISCOUNT),
    FREEBIE_PROMOTION("증정 이벤트", PromotionType.FREEBIE);

    private final String description;
    private final PromotionType promotionType;

    Promotion(String description, PromotionType promotionType) {
        this.description = description;
        this.promotionType = promotionType;
    }

    public String getDescription() {
        return description;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }
}
