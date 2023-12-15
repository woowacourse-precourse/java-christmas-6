package christmas.domain.constants;

import christmas.service.ChristmasPromotion;
import christmas.service.GiftPromotion;
import christmas.service.PromotionService;
import christmas.service.SpecialPromotion;
import christmas.service.WeekdayPromotion;
import christmas.service.WeekendPromotion;

public enum Promotion {
    CHRISTMAS(new ChristmasPromotion()),
    WEEKDAY(new WeekdayPromotion()),
    WEEKEND(new WeekendPromotion()),
    SPECIAL(new SpecialPromotion()),
    GIFT(new GiftPromotion());

    Promotion(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    private final PromotionService promotionService;

    public PromotionService getInstance() {
        return promotionService;
    }
}
