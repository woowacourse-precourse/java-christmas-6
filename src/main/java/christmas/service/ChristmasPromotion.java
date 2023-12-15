package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Discount;
import christmas.domain.Orders;
import java.util.Optional;

public class ChristmasPromotion implements PromotionService {
    private static final int START = 1;
    private static final int END = 25;
    private static final int INITIAL_DISCOUNT = 1000;
    private static final int INTERVAL = 100;

    @Override
    public Optional<Benefit> apply(int day, Orders orders) {
        if (isQualified(day)) {
            return Optional.of(
                    new Discount(calculate(day, orders))
            );
        }
        return Optional.empty();
    }

    public boolean isQualified(int day) {
        return day >= START && day <= END;
    }

    /**
     * 할인 예정 금액이 주문 금액보다 많은 경우, 주문 금액을 반환
     */
    private int calculate(int day, Orders orders) {
        return INITIAL_DISCOUNT + (day - 1) * INTERVAL;
    }
}
