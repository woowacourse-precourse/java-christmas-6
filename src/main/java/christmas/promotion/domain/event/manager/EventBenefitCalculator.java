package christmas.promotion.domain.event.manager;

import christmas.promotion.vo.Price;

public enum EventBenefitCalculator {
    INSTANCE;

    public Price getTotalEvnetBenefitPrice(final double discountPrice, final double giftPrice) {
        return Price.of(-1 * (discountPrice + giftPrice));
    }

    public Price getExceptedDiscountPrice(final double discountPrice) {
        return Price.of(discountPrice);
    }
}

