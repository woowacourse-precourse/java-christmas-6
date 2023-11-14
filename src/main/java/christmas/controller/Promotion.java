package christmas.controller;

import java.time.LocalDate;

public enum Promotion {
    CHRISTMAS_DDAY_PROMOTION(25, "크리스마스 디데이 할인"),
    WEEKDAY_PROMOTION(31, "평일 할인"),
    WEEKEND_PROMOTION(31, "주말 할인"),
    SPECIAL_PROMOTION(31, "특별 할인"),
    PRESENT_PROMOTION(31, "증정 이벤트");

    private final LocalDate promotionStartDate = LocalDate.of(2023, 12, 1);
    private final LocalDate promotionEndDate;
    private final String description;

    Promotion(int endDate, String description ) {
        promotionEndDate = LocalDate.of(2023, 12, endDate);
        this.description = description;
    }

    public boolean isInPromotionPeriod (LocalDate date) {
        return date.isBefore(promotionEndDate) || date.isEqual(promotionEndDate);
    }

    @Override
    public String toString() {
        return description;
    }
}
