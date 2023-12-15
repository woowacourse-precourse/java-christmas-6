package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Gift;
import christmas.domain.Orders;
import christmas.domain.constants.Menu;
import java.util.Optional;

public class GiftPromotion implements PromotionService {
    private static final int THRESHOLD = 120000;
    private static Menu menu = Menu.CHAMPAGNE;
    private static int count = 1;

    @Override
    public Optional<Benefit> apply(int day, Orders orders) {
        if (isQualified(orders)) {
            return Optional.of(
                    new Gift(menu, count)
            );
        }
        return Optional.empty();
    }

    private boolean isQualified(Orders orders) {
        return orders.calculateTotalPrice() >= THRESHOLD;
    }
}
