package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Discount;
import christmas.domain.Orders;
import java.util.Arrays;
import java.util.Optional;

public class SpecialPromotion implements PromotionService {
    private static final int[] SPECIAL_DAYS = {
            3, 10, 17, 24, 25, 31
    };
    private static final int DISCOUNT = 1000;

    @Override
    public Optional<Benefit> apply(int day, Orders orders) {
        if (isQualified(day)) {
            return Optional.of(
                    new Discount(DISCOUNT)
            );
        }
        return Optional.empty();
    }

    private boolean isQualified(int day) {
        return Arrays.stream(SPECIAL_DAYS)
                .filter(special -> special == day)
                .findAny()
                .isPresent();
    }
}
