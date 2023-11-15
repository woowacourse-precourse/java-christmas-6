package christmas.model;

import java.time.LocalDate;

public enum Promotion {
    CHRISTMAS_DDAY_PROMOTION(25, "크리스마스 디데이 할인",1000),
    WEEKDAY_PROMOTION(31, "평일 할인", 2023),
    WEEKEND_PROMOTION(31, "주말 할인", 2023),
    SPECIAL_PROMOTION(31, "특별 할인", 1000),
    PRESENT_PROMOTION(31, "증정 이벤트", 25000);

    private final LocalDate promotionStartDate = LocalDate.of(2023, 12, 1);
    private final LocalDate promotionEndDate;
    private final String description;
    private final int defaultBenefit;

    Promotion(int endDate, String description, int defaultBenefit ) {
        promotionEndDate = LocalDate.of(2023, 12, endDate);
        this.description = description;
        this.defaultBenefit = defaultBenefit;
    }

    public boolean isInPromotionPeriod (LocalDate date) {
        return date.isBefore(promotionEndDate) || date.isEqual(promotionEndDate);
    }

    public int checkPromotionDefaultBenefit() {
        return defaultBenefit;
    }

    @Override
    public String toString() {
        return description;
    }
}
