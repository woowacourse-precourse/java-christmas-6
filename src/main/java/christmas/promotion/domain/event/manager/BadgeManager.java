package christmas.promotion.domain.event.manager;

import christmas.promotion.domain.event.badge.Badge;
import christmas.promotion.domain.order.Order;
import christmas.promotion.vo.Price;

import java.util.Arrays;

public enum BadgeManager {
    INSTANCE;

    public Badge applyEventBadge(final Order order, final double discountPrice, final double giftPrice) {
        Price totalEventPrice = Price.of(discountPrice + giftPrice);

        return Arrays.stream(Badge.values())
                .filter(badge -> badge.isPossibleEvent(order.getDate(), totalEventPrice))
                .findFirst() // Badge.values() 순서는 threshold 가 큰 순서
                .orElse(Badge.NONE);
    }
}

