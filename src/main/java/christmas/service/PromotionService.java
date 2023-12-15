package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Orders;
import java.util.Optional;

public interface PromotionService {
    Optional<Benefit> apply(int day, Orders orders);
}
