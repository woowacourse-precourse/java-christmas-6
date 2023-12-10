package christmas.model.promotion.freebie;

import christmas.model.order.Order;
import christmas.model.promotion.PromotionManager;

public interface FreebiePolicy {
    void apply(Order order, PromotionManager manager);

    boolean isApplicable(Order order);
}
