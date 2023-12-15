package christmas.domain.constants;

import christmas.service.ChristmasPromotion;
import christmas.service.GiftPromotion;
import christmas.service.PromotionService;
import christmas.service.SpecialPromotion;
import christmas.service.WeekdayPromotion;
import christmas.service.WeekendPromotion;

public enum Promotion {
    CHRISTMAS(new ChristmasPromotion(), "크리스마스 디데이 할인"),
    WEEKDAY(new WeekdayPromotion(), "평일 할인"),
    WEEKEND(new WeekendPromotion(), "주말 할인"),
    SPECIAL(new SpecialPromotion(), "특별 할인"),
    GIFT(new GiftPromotion(), "증정 이벤트");

    private final PromotionService promotionService;
    private final String name;

    Promotion(PromotionService promotionService, String name) {
        this.promotionService = promotionService;
        this.name = name;
    }

    public PromotionService getInstance() {
        return promotionService;
    }

    public String getName() {
        return name;
    }
}
